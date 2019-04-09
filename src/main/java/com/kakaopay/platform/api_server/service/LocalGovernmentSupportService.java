package com.kakaopay.platform.api_server.service;


import com.kakaopay.platform.api_server.model.dto.request.LocalGovernmentSupportRequest;
import com.kakaopay.platform.api_server.model.dto.response.LocalGovernmentSupportResponse;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface LocalGovernmentSupportService {

    /**
     * 지자체 지원정보 목록 조회
     *
     * @return
     */
    List<LocalGovernmentSupportResponse> getLocalGovernmentSupportList();

    /**
     * 지자체명으로 지원정보 조회
     *
     * @param region
     * @return
     */
    LocalGovernmentSupportResponse getLocalGovernmentSupport(String region);

    /**
     * 지자체 지원정보 등록
     *
     * @param localGovernmentSupportRequest
     * @return
     */
    LocalGovernmentSupportResponse createLocalGovernmentSupport(LocalGovernmentSupportRequest localGovernmentSupportRequest);

    /**
     * 지자체 지원정보 수정
     *
     * @param id
     * @param localGovernmentSupportRequest
     * @return
     */
    LocalGovernmentSupportResponse updateLocalGovernmentSupport(Long id, LocalGovernmentSupportRequest localGovernmentSupportRequest);

    /**
     * 지자체 지원 정보 지원금액 내림차순으로 n개 검색
     *
     * @param limit
     * @return
     */
    String getRegionOrderBySupportLimit(Integer limit);

    /**
     * 보전 비율이 가장 작은 추천 기관명 조회
     *
     * @return
     */
    String getInstituteOrderByRate();

    /**
     * 지자체 협약 지원 정보 엑셀 업로드
     *
     * @param multipartFile
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    List<LocalGovernmentSupportRequest> uploadLocalGovernmentSupportFile(MultipartFile multipartFile) throws IOException, InvalidFormatException;
}
