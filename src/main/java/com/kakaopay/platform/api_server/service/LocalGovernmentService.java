package com.kakaopay.platform.api_server.service;


import com.kakaopay.platform.api_server.model.dto.request.LocalGovernmentRequest;
import com.kakaopay.platform.api_server.model.dto.request.LocalGovernmentSupportRequest;
import com.kakaopay.platform.api_server.model.dto.response.LocalGovernmentResponse;
import com.kakaopay.platform.api_server.model.dto.response.LocalGovernmentSupportResponse;

import java.util.List;

public interface LocalGovernmentService {

    /**
     * 지자체 기관 목록 조회
     *
     * @return List<LocalGovernmentResponse>
     */
    List<LocalGovernmentResponse> getLocalGovernmentList();

    /**
     * 지자체 기관 등록
     *
     * @param localGovernmentRequest
     * @return LocalGovernmentResponse
     */
    LocalGovernmentResponse createLocalGovernment(LocalGovernmentRequest localGovernmentRequest);
}
