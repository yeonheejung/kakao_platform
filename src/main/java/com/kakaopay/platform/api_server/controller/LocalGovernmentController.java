package com.kakaopay.platform.api_server.controller;

import com.kakaopay.platform.api_server.model.dto.request.LocalGovernmentRequest;
import com.kakaopay.platform.api_server.model.dto.request.LocalGovernmentSupportRequest;
import com.kakaopay.platform.api_server.model.dto.response.LocalGovernmentResponse;
import com.kakaopay.platform.api_server.model.dto.response.LocalGovernmentSupportResponse;
import com.kakaopay.platform.api_server.service.LocalGovernmentService;
import com.kakaopay.platform.api_server.service.LocalGovernmentSupportService;
import com.kakaopay.platform.api_server.util.ResponseObject;
import com.kakaopay.platform.api_server.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/")
@Api(value = "LocalGovernmentController", description = "지원 지자체(기관) API")
public class LocalGovernmentController {

    @Autowired
    LocalGovernmentService localGovernmentService;

    @GetMapping(value = "local-government")
    @ApiOperation(value = "지자체 검색", notes = "지자체 목록 검색 API", response = LocalGovernmentResponse.class)
    public ResponseEntity<ResponseObject> getLocalGovernmentList() {

        List<LocalGovernmentResponse> localGovernmentList = localGovernmentService.getLocalGovernmentList();

        return ResponseUtil.responseEntityAddOkData(localGovernmentList);
    }

    @PostMapping(value = "local-government")
    @ApiOperation(value = "지자체 등록", notes = "지자체 등록 API", response = LocalGovernmentSupportResponse.class)
    public ResponseEntity<ResponseObject> createLocalGovernmentSupport(@RequestBody LocalGovernmentRequest localGovernmentRequest) {

        LocalGovernmentResponse localGovernmentResponse = localGovernmentService.createLocalGovernment(localGovernmentRequest);

        return ResponseUtil.responseEntityAddOkData(localGovernmentResponse);
    }

}
