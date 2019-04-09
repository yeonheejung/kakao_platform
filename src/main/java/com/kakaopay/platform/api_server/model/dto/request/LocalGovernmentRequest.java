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
public class LocalGovernmentRequest {

    @ApiModelProperty(value = "지자체명(기관명)")
    private String localGovernmentName;

}
