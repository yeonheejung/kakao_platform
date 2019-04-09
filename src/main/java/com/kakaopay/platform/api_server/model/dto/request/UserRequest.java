package com.kakaopay.platform.api_server.model.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @ApiModelProperty(value = "아이디")
    private String userId;

    @ApiModelProperty(value = "비밀번호")
    private String password;

}
