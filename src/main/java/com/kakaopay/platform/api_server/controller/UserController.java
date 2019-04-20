package com.kakaopay.platform.api_server.controller;

import com.kakaopay.platform.api_server.common.ResponseCode;
import com.kakaopay.platform.api_server.model.dto.request.UserRequest;
import com.kakaopay.platform.api_server.model.dto.response.UserResponse;
import com.kakaopay.platform.api_server.service.UserService;
import com.kakaopay.platform.api_server.util.ResponseObject;
import com.kakaopay.platform.api_server.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(value = "UserController", description = "user API")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "user/v1")
    @ApiOperation(value = "user 등록 API", notes = "user 등록하기", response = UserResponse.class)
    public UserResponse createUser(@RequestBody UserRequest userRequest) {

        UserResponse user = userService.createUser(userRequest);

        return user;
    }

//    @GetMapping("/signin")
//    @ApiOperation(value = "로그인 API", notes = "로그인 API")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "아이디", required = true, paramType = "query", dataType = "String"),
//            @ApiImplicitParam(name = "password", value = "비밀번호", required = true, paramType = "query", dataType = "String")
//    })
//    public ResponseEntity<ResponseObject> signin(
//            @RequestParam("id") String id,
//            @RequestParam("password") String password
//    ) throws Exception {
//
//        String accessToken = userService.signin(id, password);
//
//        return ResponseUtil.responseEntityAddOkData(accessToken);
//    }
}
