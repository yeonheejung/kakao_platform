package com.kakaopay.platform.api_server.service.impl;

import com.kakaopay.platform.api_server.exception.FileUploadException;
import com.kakaopay.platform.api_server.exception.LocalGovernmentSupportDuplicationException;
import com.kakaopay.platform.api_server.exception.NotFoundLocalGovernmentSupportException;
import com.kakaopay.platform.api_server.model.LocalGovernment;
import com.kakaopay.platform.api_server.model.LocalGovernmentSupport;
import com.kakaopay.platform.api_server.model.dto.request.LocalGovernmentRequest;
import com.kakaopay.platform.api_server.model.dto.request.LocalGovernmentSupportRequest;
import com.kakaopay.platform.api_server.model.dto.response.LocalGovernmentResponse;
import com.kakaopay.platform.api_server.model.dto.response.LocalGovernmentSupportResponse;
import com.kakaopay.platform.api_server.model.type.MoneyType;
import com.kakaopay.platform.api_server.repository.LocalGovernmentRepository;
import com.kakaopay.platform.api_server.repository.LocalGovernmentSupportRepository;
import com.kakaopay.platform.api_server.service.LocalGovernmentService;
import com.kakaopay.platform.api_server.service.LocalGovernmentSupportService;
import com.kakaopay.platform.api_server.util.ExcelUtil;
import com.kakaopay.platform.api_server.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class LocalGovernmentSupportServiceImpl implements LocalGovernmentSupportService {

    private final LocalGovernmentSupportRepository localGovernmentSupportRepository;
    private final LocalGovernmentRepository localGovernmentRepository;
    private final LocalGovernmentService localGovernmentService;

    @Override
    public List<LocalGovernmentSupportResponse> getLocalGovernmentSupportList() {

        return localGovernmentSupportRepository.findAll().stream().map(
                localGovernmentSupport -> new LocalGovernmentSupportResponse(
                        localGovernmentSupport.getId(),
                        localGovernmentSupport.getLocalGovernment().getLocalGovernmentName(),
                        localGovernmentSupport.getTarget(),
                        localGovernmentSupport.getSupportUsage(),
                        MoneyType.findMoneyStringType(localGovernmentSupport.getSupportLimit()),
                        StringUtil.convertRateToString(localGovernmentSupport.getRateMin(), localGovernmentSupport.getRateMax()),
                        localGovernmentSupport.getInstitute(),
                        localGovernmentSupport.getMgmt(),
                        localGovernmentSupport.getReception())).collect(Collectors.toList());
    }

    @Override
    public LocalGovernmentSupportResponse getLocalGovernmentSupport(String region) {

        LocalGovernment localGovernment = getLocalGovernmentByRegion(region);

        if (Objects.isNull(localGovernment)) {
            throw new NotFoundLocalGovernmentSupportException();
        }
        LocalGovernmentSupport localGovernmentSupport = localGovernmentSupportRepository.findByLocalGovernment(localGovernment);

        if (Objects.isNull(localGovernmentSupport)) {
            throw new NotFoundLocalGovernmentSupportException();
        }
        String rate = StringUtil.convertRateToString(localGovernmentSupport.getRateMin(), localGovernmentSupport.getRateMax());
        String limit = MoneyType.findMoneyStringType(localGovernmentSupport.getSupportLimit());

        return LocalGovernmentSupportResponse.builder()
                .id(localGovernmentSupport.getId())
                .region(localGovernmentSupport.getLocalGovernment().getLocalGovernmentName())
                .target(localGovernmentSupport.getTarget())
                .usage(localGovernmentSupport.getSupportUsage())
                .limit(limit)
                .rate(rate)
                .institute(localGovernmentSupport.getInstitute())
                .mgmt(localGovernmentSupport.getMgmt())
                .reception(localGovernmentSupport.getReception())
                .build();
    }

    @Override
    public LocalGovernmentSupportResponse createLocalGovernmentSupport(LocalGovernmentSupportRequest localGovernmentSupportRequest) {

        LocalGovernment localGovernment = getLocalGovernmentByRegion(localGovernmentSupportRequest.getRegion());

        // 지자체 코드 없으면 새로 생성
        if (Objects.isNull(localGovernment)) {
            localGovernment = createLocalGovernment(localGovernmentSupportRequest);
        }
        LocalGovernmentSupport localGovernmentSupport = localGovernmentSupportRepository.findByLocalGovernment(localGovernment);
        // 지자체 지원정보 중복 체크
        if (Objects.nonNull(localGovernmentSupport)) {
            throw new LocalGovernmentSupportDuplicationException();
        }
        localGovernmentSupport = new LocalGovernmentSupport();

        // 이차보전 min, max 분리
        Map<String, BigDecimal> rateMap = StringUtil.convertStringRateToBigDecimal(localGovernmentSupportRequest.getRate());
        Long moneyIntType = MoneyType.findMoneyIntType(localGovernmentSupportRequest.getLimit());

        localGovernmentSupport.setLocalGovernment(localGovernment);
        localGovernmentSupport.setTarget(localGovernmentSupportRequest.getTarget());
        localGovernmentSupport.setSupportUsage(localGovernmentSupportRequest.getUsage());
        localGovernmentSupport.setRateMin(rateMap.get("rateMin"));
        localGovernmentSupport.setRateMax(rateMap.get("rateMax"));
        localGovernmentSupport.setSupportLimit(moneyIntType);
        localGovernmentSupport.setInstitute(localGovernmentSupportRequest.getInstitute());
        localGovernmentSupport.setMgmt(localGovernmentSupportRequest.getMgmt());
        localGovernmentSupport.setReception(localGovernmentSupportRequest.getReception());

        localGovernmentSupportRepository.save(localGovernmentSupport);

        String rate = StringUtil.convertRateToString(localGovernmentSupport.getRateMin(), localGovernmentSupport.getRateMax());
        String limit = MoneyType.findMoneyStringType(localGovernmentSupport.getSupportLimit());

        return LocalGovernmentSupportResponse.builder()
                .id(localGovernmentSupport.getId())
                .region(localGovernmentSupport.getLocalGovernment().getLocalGovernmentName())
                .target(localGovernmentSupport.getTarget())
                .usage(localGovernmentSupport.getSupportUsage())
                .limit(limit)
                .rate(rate)
                .institute(localGovernmentSupport.getInstitute())
                .mgmt(localGovernmentSupport.getMgmt())
                .reception(localGovernmentSupport.getReception())
                .build();
    }

    @Override
    public LocalGovernmentSupportResponse updateLocalGovernmentSupport(Long id, LocalGovernmentSupportRequest localGovernmentSupportRequest) {

        Optional<LocalGovernmentSupport> localGovernmentSupport = localGovernmentSupportRepository.findById(id);

        if (!localGovernmentSupport.isPresent()) {
            throw new NotFoundLocalGovernmentSupportException();
        }
        LocalGovernmentSupport existLocalGovernmentSupport = localGovernmentSupport.get();

        LocalGovernment localGovernment = getLocalGovernmentByRegion(localGovernmentSupportRequest.getRegion());

        // 지자체 코드 없으면 새로 생성
        if (Objects.isNull(localGovernment)) {
            localGovernment = createLocalGovernment(localGovernmentSupportRequest);
        }
        // 이차보전 min, max 분리
        Map<String, BigDecimal> rateMap = StringUtil.convertStringRateToBigDecimal(localGovernmentSupportRequest.getRate());
        Long moneyIntType = MoneyType.findMoneyIntType(localGovernmentSupportRequest.getLimit());

        existLocalGovernmentSupport.setLocalGovernment(localGovernment);
        existLocalGovernmentSupport.setTarget(localGovernmentSupportRequest.getTarget());
        existLocalGovernmentSupport.setSupportUsage(localGovernmentSupportRequest.getUsage());
        existLocalGovernmentSupport.setRateMin(rateMap.get("rateMin"));
        existLocalGovernmentSupport.setRateMax(rateMap.get("rateMax"));
        existLocalGovernmentSupport.setSupportLimit(moneyIntType);
        existLocalGovernmentSupport.setInstitute(localGovernmentSupportRequest.getInstitute());
        existLocalGovernmentSupport.setMgmt(localGovernmentSupportRequest.getMgmt());
        existLocalGovernmentSupport.setReception(localGovernmentSupportRequest.getReception());

        localGovernmentSupportRepository.save(localGovernmentSupport.get());

        String rate = StringUtil.convertRateToString(existLocalGovernmentSupport.getRateMin(), existLocalGovernmentSupport.getRateMax());
        String limit = MoneyType.findMoneyStringType(existLocalGovernmentSupport.getSupportLimit());

        LocalGovernmentSupportResponse localGovernmentSupportResponse = LocalGovernmentSupportResponse.builder()
                .id(existLocalGovernmentSupport.getId())
                .region(existLocalGovernmentSupport.getLocalGovernment().getLocalGovernmentName())
                .target(existLocalGovernmentSupport.getTarget())
                .usage(existLocalGovernmentSupport.getSupportUsage())
                .limit(limit)
                .rate(rate)
                .institute(existLocalGovernmentSupport.getInstitute())
                .mgmt(existLocalGovernmentSupport.getMgmt())
                .reception(existLocalGovernmentSupport.getReception())
                .build();

        return localGovernmentSupportResponse;
    }

    @Override
    public String getRegionOrderBySupportLimit(Integer count) {

        List<LocalGovernmentSupport> localGovernmentSupportList = localGovernmentSupportRepository.findByOrderBySupportLimitAndAverageRate(count);

        return localGovernmentSupportList.stream()
                .map(LocalGovernmentSupport::getLocalGovernment)
                .map(LocalGovernment::getLocalGovernmentName)
                .collect(Collectors.joining(","));
    }

    @Override
    public String getInstituteOrderByRate() {

        LocalGovernmentSupport localGovernmentSupport = localGovernmentSupportRepository.findTopByOrderByAverageRate();

        return localGovernmentSupport.getInstitute();
    }

    @Override
    public List<LocalGovernmentSupportRequest> uploadLocalGovernmentSupportFile(MultipartFile multipartFile) {

        try {
            List<LocalGovernmentSupportRequest> localGovernmentSupportRequestList =
                    ExcelUtil.readFileToList(multipartFile, LocalGovernmentSupportRequest::getLocalGovernmentSupportRequestByRow);

            List<LocalGovernmentSupport> localGovernmentSupportList = new ArrayList<>();

            for (LocalGovernmentSupportRequest localGovernmentSupportItem : localGovernmentSupportRequestList) {

                LocalGovernment localGovernment = getLocalGovernmentByRegion(localGovernmentSupportItem.getRegion());

                // 지자체 코드 없으면 새로 생성
                if (Objects.isNull(localGovernment) && Objects.nonNull(localGovernmentSupportItem.getRegion())) {
                    createLocalGovernment(localGovernmentSupportItem);
                }
                LocalGovernmentSupport localGovernmentSupport = localGovernmentSupportRepository.findByLocalGovernment(localGovernment);
                // 지자체 지원정보 중복 체크
                if (Objects.isNull(localGovernmentSupport) && Objects.nonNull(localGovernment)) {
                    localGovernmentSupport = new LocalGovernmentSupport();

                    // 이차보전 min, max 분리
                    Map<String, BigDecimal> rateMap = StringUtil.convertStringRateToBigDecimal(localGovernmentSupportItem.getRate());
                    Long moneyIntType = MoneyType.findMoneyIntType(localGovernmentSupportItem.getLimit());

                    localGovernmentSupport.setLocalGovernment(localGovernment);
                    localGovernmentSupport.setTarget(localGovernmentSupportItem.getTarget());
                    localGovernmentSupport.setSupportUsage(localGovernmentSupportItem.getUsage());
                    localGovernmentSupport.setRateMin(rateMap.get("rateMin"));
                    localGovernmentSupport.setRateMax(rateMap.get("rateMax"));
                    localGovernmentSupport.setSupportLimit(moneyIntType);
                    localGovernmentSupport.setInstitute(localGovernmentSupportItem.getInstitute());
                    localGovernmentSupport.setMgmt(localGovernmentSupportItem.getMgmt());
                    localGovernmentSupport.setReception(localGovernmentSupportItem.getReception());

                    localGovernmentSupportList.add(localGovernmentSupport);
                }
            }
            localGovernmentSupportRepository.saveAll(localGovernmentSupportList);

            return localGovernmentSupportRequestList;

        } catch (Exception e) {
            throw new FileUploadException();
        }
    }

    /**
     * 지자체명으로 지자체(기관) 가져오기
     *
     * @param region
     * @return
     */
    private LocalGovernment getLocalGovernmentByRegion(String region) {

        String localGovernmentName = region.replaceAll("\\p{Z}", "");

        return localGovernmentRepository.findByLocalGovernmentName(localGovernmentName);
    }

    /**
     * 지자체(기관) 등록
     *
     * @param localGovernmentSupportRequest
     */
    private LocalGovernment createLocalGovernment(LocalGovernmentSupportRequest localGovernmentSupportRequest) {
        LocalGovernmentRequest localGovernmentRequest = LocalGovernmentRequest.builder()
                .localGovernmentName(localGovernmentSupportRequest.getRegion())
                .build();
        LocalGovernmentResponse localGovernmentResponse = localGovernmentService.createLocalGovernment(localGovernmentRequest);

        return localGovernmentRepository.findByLocalGovernmentName(localGovernmentResponse.getLocalGovernmentName());
    }
}
