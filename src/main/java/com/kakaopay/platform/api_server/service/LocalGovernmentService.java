package com.kakaopay.platform.api_server.service;


import com.kakaopay.platform.api_server.model.dto.request.LocalGovernmentRequest;
import com.kakaopay.platform.api_server.model.dto.request.LocalGovernmentSupportRequest;
import com.kakaopay.platform.api_server.model.dto.response.LocalGovernmentResponse;
import com.kakaopay.platform.api_server.model.dto.response.LocalGovernmentSupportResponse;

import java.util.List;

public interface LocalGovernmentService {

    List<LocalGovernmentResponse> getLocalGovernmentList();

    LocalGovernmentResponse createLocalGovernment(LocalGovernmentRequest localGovernmentRequest);
}
