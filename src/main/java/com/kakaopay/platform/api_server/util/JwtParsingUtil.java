//package com.kakaopay.platform.api_server.util;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.SignatureVerificationException;
//import com.auth0.jwt.exceptions.TokenExpiredException;
//import com.kakaopay.platform.api_server.model.dto.jwt.JwtHeader;
//import com.kakaopay.platform.api_server.model.dto.jwt.JwtObject;
//import com.kakaopay.platform.api_server.model.dto.jwt.JwtPayload;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.codec.binary.Base64;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.LinkedHashMap;
//
//@Component
//@Slf4j
//public class JwtParsingUtil {
//
//    public static JwtObject jwtDecode(String str) throws Exception {
//        if (StringUtils.isEmpty(str) || str.split("\\.").length != 3) {
//            throw new IllegalArgumentException(str);
//        }
//
//        String[] splitData = str.split("\\.");
//        String header = splitData[0];
//        String payload = splitData[1];
//
//        header = changeBase64(header);
//        payload = changeBase64(payload);
//
//        JwtHeader jwtHeader = (JwtHeader) JsonConverter.jsonToObject(header, JwtHeader.class);
//        JwtPayload jwtPayload = (JwtPayload) JsonConverter.jsonToObject(payload, JwtPayload.class);
//
//        JwtObject jwtObject = new JwtObject();
//        jwtObject.setEncryptionAlgorithm(jwtHeader.getAlg());
//        jwtObject.setTypeOfToken(jwtHeader.getTyp());
//        jwtObject.setAudience(jwtPayload.getAud());
//        jwtObject.setUserId(Long.parseLong(jwtPayload.getUserName()));
//        jwtObject.setScope(jwtPayload.getScope());
//        jwtObject.setAti(jwtPayload.getAti());
//        jwtObject.setExpiration(new Date(jwtPayload.getExp() * 1000));
//        jwtObject.setExpirationNum(jwtPayload.getExp());
//        jwtObject.setJwtId(jwtPayload.getJti());
//        jwtObject.setClientId(jwtPayload.getClientId());
//
//        return jwtObject;
//    }
//
//    public static LinkedHashMap<String, Object> setCustomTokenServiceObject(JwtObject obj) throws Exception {
//        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
//        ArrayList<String> aud = new ArrayList<>();
//        String[] audList = obj.getAudience();
//
//        for (String a : audList) {
//            aud.add(a);
//        }
//
//        map.put("aud", aud);
//        map.put("exp", obj.getExpirationNum());
//        map.put("user_name", String.valueOf(obj.getUserId()));
//        map.put("jti", obj.getJwtId());
//        map.put("client_id", obj.getClientId());
//
//        ArrayList<String> scope = new ArrayList<>();
//        String[] scopeList = obj.getScope();
//
//        for (String s : scopeList) {
//            scope.add(s);
//        }
//
//        map.put("scope", scope);
//
//        return map;
//    }
//
//    public static Boolean checkJwtTokenSigningKey(String token, String signingKey) throws SignatureVerificationException {
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(signingKey);
//            JWTVerifier verifier = JWT.require(algorithm).build();
//            verifier.verify(token);
//        } catch (TokenExpiredException e) {
//            // 다음 프로세스에서 체크
//            return true;
//        }
//
//
//        return true;
//    }
//
//    private static String changeBase64(String str) {
//        Base64 base64 = new Base64(true);
//
//        return new String(base64.decode(str));
//    }
//}
