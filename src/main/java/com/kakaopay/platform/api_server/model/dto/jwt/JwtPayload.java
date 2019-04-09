package com.kakaopay.platform.api_server.model.dto.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtPayload {

    @JsonProperty("aud")
    private String[] aud;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("scope")
    private String[] scope;

    @JsonProperty("ati")
    private String ati;

    @JsonProperty("exp")
    private Long exp;

    @JsonProperty("jti")
    private String jti;

    @JsonProperty("client_id")
    private String clientId;
}
