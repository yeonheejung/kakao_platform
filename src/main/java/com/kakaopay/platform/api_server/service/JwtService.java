package com.kakaopay.platform.api_server.service;

import com.kakaopay.platform.api_server.model.dto.request.UserRequest;
import com.kakaopay.platform.api_server.model.dto.response.UserResponse;
import org.springframework.data.domain.Page;

public interface JwtService {

    UserResponse generateJwtToken(UserRequest userRequest);

}
