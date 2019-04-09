//package com.kakaopay.platform.api_server.config.oauth;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//import org.springframework.security.web.AuthenticationEntryPoint;
//
//@Configuration
//@EnableResourceServer
//@Slf4j
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
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
//
//    @Autowired
//    CustomRemoteTokenServices remoteTokenServices;
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources
//                .resourceId(resourceIds)
//                .tokenServices(remoteTokenServices)
//                .authenticationEntryPoint(customAuthEntryPoint());
//
//
//    }
//
//    @Bean
//    public TokenStore tokenStore() {
//        return new JwtTokenStore(accessTokenConverter());
//    }
//
//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey(signingKey);
//        return converter;
//    }
//
//    @Primary
//    @Bean
//    public CustomRemoteTokenServices tokenServices() {
//        try {
//            CustomRemoteTokenServices tokenServices = new CustomRemoteTokenServices();
//            tokenServices.setCheckTokenEndpointUrl(tokenInfoUri);
//            tokenServices.setClientId(clientId);
//            tokenServices.setClientSecret(clientSecret);
//            return tokenServices;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//    @Bean
//    public AuthenticationEntryPoint customAuthEntryPoint() {
//        return new CustomAuthenticationEntryPoint();
//    }
//}
