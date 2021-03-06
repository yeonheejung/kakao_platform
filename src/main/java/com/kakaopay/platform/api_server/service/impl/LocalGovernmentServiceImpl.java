package com.kakaopay.platform.api_server.service.impl;

import com.kakaopay.platform.api_server.exception.LocalGovernmentDuplicationException;
import com.kakaopay.platform.api_server.model.LocalGovernment;
import com.kakaopay.platform.api_server.model.dto.request.LocalGovernmentRequest;
import com.kakaopay.platform.api_server.model.dto.response.LocalGovernmentResponse;
import com.kakaopay.platform.api_server.repository.LocalGovernmentRepository;
import com.kakaopay.platform.api_server.service.LocalGovernmentService;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class LocalGovernmentServiceImpl implements LocalGovernmentService {

    private final LocalGovernmentRepository localGovernmentRepository;

    @Override
    public List<LocalGovernmentResponse> getLocalGovernmentList() {

        return localGovernmentRepository.findAll().stream().map(
                localGovernment -> new LocalGovernmentResponse(
                        makeLocalGovernmentRegion(localGovernment.getLocalGovernmentCode()),
                        localGovernment.getLocalGovernmentName())).collect(toList());
    }

    @Override
    public LocalGovernmentResponse createLocalGovernment(LocalGovernmentRequest localGovernmentRequest) {

        // 지자체명 공백 제거
        String localGovernmentName = localGovernmentRequest.getLocalGovernmentName().replaceAll("\\p{Z}", "");

        LocalGovernment localGovernment = localGovernmentRepository.findByLocalGovernmentName(localGovernmentName);

        if (Objects.nonNull(localGovernment)) {
            throw new LocalGovernmentDuplicationException();
        }
        localGovernment = new LocalGovernment();

        localGovernment.setLocalGovernmentName(localGovernmentRequest.getLocalGovernmentName());

        localGovernmentRepository.save(localGovernment);

        LocalGovernmentResponse localGovernmentResponse = LocalGovernmentResponse.builder()
                .localGovernmentCode(makeLocalGovernmentRegion(localGovernment.getLocalGovernmentCode()))
                .localGovernmentName(localGovernment.getLocalGovernmentName())
                .build();

        return localGovernmentResponse;
    }


    /**
     * 지자체 코드 변환
     *
     * @param localGovernmentCode
     * @return "reg**"
     */
    private String makeLocalGovernmentRegion(Long localGovernmentCode) {

        return "reg" + localGovernmentCode;
    }
}
