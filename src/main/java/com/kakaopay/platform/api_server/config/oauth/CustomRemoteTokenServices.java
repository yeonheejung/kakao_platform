//package com.kakaopay.platform.api_server.config.oauth;
//
//import com.kakaopay.platform.api_server.client.ConnectionAuth;
//import com.kakaopay.platform.api_server.model.User;
//import com.kakaopay.platform.api_server.model.dto.jwt.JwtObject;
//import com.kakaopay.platform.api_server.model.type.TokenValidateType;
//import com.kakaopay.platform.api_server.repository.UserRepository;
//import com.kakaopay.platform.api_server.util.JwtParsingUtil;
//import kr.co.bomapp.apps.api_server.common.v1.model.Planner;
//import kr.co.bomapp.apps.api_server.common.v1.model.dto.jwt.JwtObject;
//import kr.co.bomapp.apps.api_server.common.v1.model.type.TokenValidateType;
//import kr.co.bomapp.apps.api_server.common.v1.repository.userRepository;
//import kr.co.bomapp.apps.api_server.common.v1.util.JwtParsingUtil;
//import kr.co.bomapp.apps.api_server.v1.client.ConnectionAuth;
//import kr.co.bomapp.apps.api_server.v1.exception.AccessTokenExpiredException;
//import kr.co.bomapp.apps.api_server.v1.exception.NotFoundResultException;
//import kr.co.bomapp.apps.api_server.v1.exception.RefreshTokenExpiredException;
//import kr.co.bomapp.apps.api_server.v1.exception.RefreshTokenNotExistException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
//import org.springframework.util.StringUtils;
//
//import java.util.LinkedHashMap;
//
//@Slf4j
//public class CustomRemoteTokenServices extends RemoteTokenServices {
//
//    @Autowired
//    private ConnectionAuth connectionAuth;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Value("${security.jwt.resource-ids}")
//    private String resourceIds;
//
//    @Value("${security.signing-key}")
//    private String signingKey;
//
//    @Value("${security.oauth2.resource.token-info-uri}")
//    private String tokenInfoUri;
//
//    @Value("${security.oauth2.client.client-id}")
//    private String clientId;
//
//    @Value("${security.oauth2.client.client-secret}")
//    private String clientSecret;
//
//    @Value("${spring.profiles.active}")
//    private String activeProfiles;
//
//    @Override
//    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
//
//        if (StringUtils.isEmpty(accessToken)) throw new InvalidTokenException(TokenValidateType.BROKEN_ACCESS_TOKEN.getValue());
//
//        try {
//            // 데이터베이스 조회
//                User user = this.userRepository.findByJwtAccessTokenAndLeaveDateIsNull(accessToken);
//                if (user == null) {
//                    // 일치하는 회원이 없음
//                    throw new NotFoundResultException();
//                }
//
//                if (StringUtils.isEmpty(planner.getJwtRefreshToken())) {
//                    throw new RefreshTokenNotExistException();
//                }
//
//                if (!connectionAuth.accessTokenExpiredCheck(planner.getJwtRefreshToken())) {
//                    throw new RefreshTokenExpiredException();
//                }
//
//            if (!connectionAuth.accessTokenExpiredCheck(accessToken)) {
//                throw new AccessTokenExpiredException();
//            }
//
//            JwtObject jwtObject = JwtParsingUtil.jwtDecode(accessToken);
//            LinkedHashMap<String, Object> map = JwtParsingUtil.setCustomTokenServiceObject(jwtObject);
//
//
//            return this.getAccessTokenConverter().extractAuthentication(map);
//        } catch (RefreshTokenNotExistException e) {
//            throw new InvalidTokenException(TokenValidateType.REFRESH_TOKEN_NOT_FOUND.getValue());
//        } catch (RefreshTokenExpiredException e) {
//            throw new InvalidTokenException(TokenValidateType.EXPIRED_REFRESH_TOKEN.getValue());
//        } catch (AccessTokenExpiredException e) {
//            throw new InvalidTokenException(TokenValidateType.EXPIRED_ACCESS_TOKEN.getValue());
//        } catch (NotFoundResultException e) {
//            throw new InvalidTokenException(TokenValidateType.TOKEN_NOT_FOUND.getValue());
//        } catch (Exception e) {
//            throw new InvalidTokenException(TokenValidateType.TOKEN_CHECK_EXCEPTION.getValue());
//        }
//    }
//
//    @Bean
//    public static AccessTokenConverter getAccessTokenConverter() {
//        return new DefaultAccessTokenConverter();
//    }
//}
