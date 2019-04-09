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
public class LocalGovernmentSupportResponse {

    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "지자체명(기관명)")
    private String region;
    @ApiModelProperty(value = "지원대상")
    private String target;
    @ApiModelProperty(value = "용도")
    private String usage;
    @ApiModelProperty(value = "지원한도")
    private String limit;
    @ApiModelProperty(value = "이차보전")
    private String rate;
    @ApiModelProperty(value = "추천기관")
    private String institute;
    @ApiModelProperty(value = "관리점")
    private String mgmt;
    @ApiModelProperty(value = "취급점")
    private String reception;
}
