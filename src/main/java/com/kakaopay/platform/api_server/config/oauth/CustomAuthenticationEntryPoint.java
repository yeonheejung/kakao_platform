//package com.kakaopay.platform.api_server.config.oauth;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import kr.co.bomapp.apps.api_server.common.v1.code.response.ResponseCode;
//import kr.co.bomapp.apps.api_server.common.v1.model.type.TokenValidateType;
//import kr.co.bomapp.apps.api_server.common.v1.util.ResponseObject;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.OutputStream;
//
//public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
//
//    @Override
//    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
//        httpServletResponse.setStatus(HttpStatus.OK.value());
//        httpServletResponse.setCharacterEncoding("UTF-8");
//        httpServletResponse.setContentType("application/json");
//        OutputStream out = httpServletResponse.getOutputStream();
//        com.fasterxml.jackson.databind.ObjectMapper mapper = new ObjectMapper();
//        mapper.writeValue(out, setMessage(e.getMessage()));
//        out.flush();
//    }
//
//    private ResponseObject setMessage(String message) {
//
//        ResponseObject res = new ResponseObject();
//
//        if (TokenValidateType.EXPIRED_REFRESH_TOKEN.getValue().equals(message)) {
//            res.setCode(ResponseCode.ERROR_TOKEN_EXPIRED_REFRESH.getCode());
//            res.setMessage(ResponseCode.ERROR_TOKEN_EXPIRED_REFRESH.getMessage());
//        } else if (TokenValidateType.EXPIRED_ACCESS_TOKEN.getValue().equals(message)) {
//            res.setCode(ResponseCode.ERROR_TOKEN_EXPIRED_ACCESS.getCode());
//            res.setMessage(ResponseCode.ERROR_TOKEN_EXPIRED_ACCESS.getMessage());
//        } else if (TokenValidateType.REFRESH_TOKEN_NOT_FOUND.getValue().equals(message) || TokenValidateType.TOKEN_NOT_FOUND.getValue().equals(message)) {
//            res.setCode(ResponseCode.ERROR_TOKEN_NOT_FOUND.getCode());
//            res.setMessage(ResponseCode.ERROR_TOKEN_NOT_FOUND.getMessage());
//        } else if (TokenValidateType.BROKEN_ACCESS_TOKEN.getValue().equals(message) || TokenValidateType.BROKEN_REFRESH_TOKEN.getValue().equals(message)) {
//            res.setCode(ResponseCode.ERROR_TOKEN_BROKEN.getCode());
//            res.setMessage(ResponseCode.ERROR_TOKEN_BROKEN.getMessage());
//        } else {
//            res.setCode(ResponseCode.ERROR_TOKEN_NOT_FOUND_HEADER.getCode());
//            res.setMessage(ResponseCode.ERROR_TOKEN_NOT_FOUND_HEADER.getMessage());
//        }
//
//        return res;
//    }
//}
