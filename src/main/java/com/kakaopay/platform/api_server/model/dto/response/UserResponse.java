package com.kakaopay.platform.api_server.model.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "사용자 id")
    private String userId;
    @ApiModelProperty(value = "이름")
    private String name;

}
