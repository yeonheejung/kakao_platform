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
public class LocalGovernmentResponse {

    @ApiModelProperty(value = "지자체 코드")
    private String localGovernmentCode;

    @ApiModelProperty(value = "지자체명")
    private String localGovernmentName;
}
