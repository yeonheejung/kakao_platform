//package com.kakaopay.platform.api_server.config.oauth;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring()
//                .antMatchers(
//                        "/v2/api-docs",
//                        "/configuration/ui",
//                        "/swagger-resources/**",
//                        "/configuration/**",
//                        "/swagger-ui.html",
//                        "/webjars/**",
//                        "/ins-company/v1/**",
//                        "/planner/v1/**",
//                        "/planner/token/**",
//                        "/my-planner/v1/**",
//                        "/my-planner-server/v1/**",
//                        "/customer-server/my-planner/v1/**",
//                        "/customer/v1/push");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.headers().frameOptions().disable();
//        http.httpBasic();
//
//        http.authorizeRequests()
//                .antMatchers()
//                .permitAll()
//                .antMatchers("/api/**").access("oauth2.hasAnyScope('read', 'write')");
//    }
//}
