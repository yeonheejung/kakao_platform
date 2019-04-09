package com.kakaopay.platform.api_server.model.dto.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtObject {

    private String encryptionAlgorithm;
    private String typeOfToken;
    private String[] audience;
    private Long userId;
    private String[] scope;
    private String ati;
    private Date expiration;
    private Long expirationNum;
    private String jwtId;
    private String clientId;
}
