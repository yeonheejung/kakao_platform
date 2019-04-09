package com.kakaopay.platform.api_server.model.dto.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtHeader {

    @JsonProperty("alg")
    private String alg;

    @JsonProperty("typ")
    private String typ;
}
