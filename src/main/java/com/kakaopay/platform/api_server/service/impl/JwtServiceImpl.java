//package com.kakaopay.platform.api_server.service.impl;
//
//import com.kakaopay.platform.api_server.client.ConnectionAuth;
//import com.kakaopay.platform.api_server.exception.UserDuplicationException;
//import com.kakaopay.platform.api_server.model.User;
//import com.kakaopay.platform.api_server.model.dto.request.UserRequest;
//import com.kakaopay.platform.api_server.model.dto.response.TokenInfoResponse;
//import com.kakaopay.platform.api_server.model.dto.response.UserResponse;
//import com.kakaopay.platform.api_server.repository.UserRepository;
//import com.kakaopay.platform.api_server.service.JwtService;
//import com.kakaopay.platform.api_server.service.UserService;
//import com.kakaopay.platform.api_server.util.StringUtil;
//import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.Objects;
//
//@Service
//@Transactional
//@Slf4j
//public class JwtServiceImpl implements JwtService {
//
//    @Override
//    public String generatorJwtToken() {
//        String jwt = Jwts.builder()
//                .setHeaderParam("typ", "JWT")
//                .setHeaderParam("regDate", System.currentTimeMillis())
//                .setSubject(subject)
//                .claim(key, data)
//                .signWith(SignatureAlgorithm.HS256, this.generateKey())
//                .compact();
//        return jwt;
//    }
//
//}
