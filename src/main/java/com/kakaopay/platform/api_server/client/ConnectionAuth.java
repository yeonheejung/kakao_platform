//package com.kakaopay.platform.api_server.client;
//
//import com.kakaopay.platform.api_server.exception.NoParamsException;
//import com.kakaopay.platform.api_server.model.dto.jwt.JwtObject;
//import com.kakaopay.platform.api_server.model.dto.response.TokenInfoResponse;
//
//import com.kakaopay.platform.api_server.util.JwtParsingUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
//import org.springframework.security.oauth2.client.OAuth2RestOperations;
//import org.springframework.security.oauth2.client.OAuth2RestTemplate;
//import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
//import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
//import org.springframework.security.oauth2.client.token.AccessTokenRequest;
//import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
//import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import java.util.*;
//
//@Component
//@Slf4j
//public class ConnectionAuth {
////
////    @Value("${security.server.url}")
////    private String serverUrl;
////
////    @Value("${security.server.access-token-url}")
////    private String serverAccessTokenUrl;
//
//    /**
//     * Access token 발급
//     *
//     * @param id
//     * @return TokenInfoResponse
//     */
//    public TokenInfoResponse generateAuthKey(String id, String clientId, String clientSecret) throws Exception {
//        try {
//            return this.getTokenInfoResponse(id, clientId, clientSecret);
//        } catch (OAuth2AccessDeniedException e) {
//            throw e;
////            throw new AccessTokenExpiredException();
//        }
//    }
//
//
//
//    /**
//     * Access token 재발급
//     *
//     * @param refreshToken
//     * @return
//     */
//    public TokenInfoResponse reissuedAccessKey(String refreshToken, String clientId, String clientSecret) throws Exception {
////        try {
//            if (StringUtils.isEmpty(refreshToken) || !this.refreshTokenExpiredCheck(refreshToken)) {
////                throw new RefreshTokenNotExistException(refreshToken);
//            }
//
//            JwtObject jwtObject = JwtParsingUtil.jwtDecode(refreshToken);
//
//            TokenInfoResponse TokenInfoResponse = this.getTokenInfoResponse(String.valueOf(jwtObject.getUserId()), clientId, clientSecret);
//
//            if (Objects.isNull(TokenInfoResponse)) {
////                throw new JwtTokenGenerateFailException();
//            }
//
//            return TokenInfoResponse;
//
////        } catch (RefreshTokenNotExistException e) {
////            throw e;
////        }
//    }
//
//    /**
//     * Access token의 만료일자 체크
//     *
//     * @param accessToken
//     * @return
//     * @throws Exception
//     */
//    public Boolean accessTokenExpiredCheck(String accessToken) throws Exception {
//        return this.tokenExpiredCheck(accessToken);
//    }
//
//    /**
//     * Refresh token의 만료일자 체크
//     *
//     * @param refreshToken refresh token
//     * @return Boolean
//     */
//    public Boolean refreshTokenExpiredCheck(String refreshToken) throws Exception {
//        return this.tokenExpiredCheck(refreshToken);
//    }
//
//    /**
//     * 토큰 만료일자 검증
//     *
//     * @param token 토큰
//     * @return true: 만료안됨, false: 만료됨
//     * @throws Exception
//     */
//    public Boolean tokenExpiredCheck(String token) throws Exception {
//        if (StringUtils.isEmpty(token)) {
//            throw new NoParamsException();
//        }
//
//        JwtObject jwtObject = JwtParsingUtil.jwtDecode(token);
//        Date expiration = jwtObject.getExpiration();
//        Date currentDate = Calendar.getInstance().getTime();
//
//        if (expiration.compareTo(currentDate) > 0) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    private TokenInfoResponse getTokenInfoResponse(String id, String clientId, String clientSecret) throws Exception {
//        OAuth2AccessToken oAuth2AccessToken = generateAccessToken(id, clientId, clientSecret).getAccessToken();
//
//        if (Objects.isNull(oAuth2AccessToken)) {
////            throw new JwtTokenGenerateFailException();
//        }
//
//        TokenInfoResponse TokenInfoResponse = new TokenInfoResponse();
//        TokenInfoResponse.setAccessToken(oAuth2AccessToken.getValue());
//        TokenInfoResponse.setRefreshToken(oAuth2AccessToken.getRefreshToken().getValue());
//
//        return TokenInfoResponse;
//    }
//
//    private OAuth2RestOperations generateAccessToken(String id, String clientId, String clientSecret) throws Exception {
//        AccessTokenRequest accessTokenRequest = new DefaultAccessTokenRequest();
//
//        return new OAuth2RestTemplate(resource(id, clientId, clientSecret), new DefaultOAuth2ClientContext(accessTokenRequest));
//    }
//
//    private OAuth2ProtectedResourceDetails resource(String id, String clientId, String clientSecret) throws Exception {
//        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
//
//        List scopes = new ArrayList<String>(2);
//        scopes.add("write");
//        scopes.add("read");
//
////        resource.setAccessTokenUri(serverAccessTokenUrl);
//        resource.setClientId(clientId);
//        resource.setClientSecret(clientSecret);
//        resource.setGrantType("password");
//        resource.setScope(scopes);
//        resource.setUsername(id);
//        resource.setPassword(id);
//
//        return resource;
//    }
//
//
//}
