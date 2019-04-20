package com.kakaopay.platform.api_server.controller;

import com.kakaopay.platform.api_server.exception.NoParamsException;
import com.kakaopay.platform.api_server.model.dto.request.LocalGovernmentSupportRequest;
import com.kakaopay.platform.api_server.model.dto.response.LocalGovernmentSupportResponse;
import com.kakaopay.platform.api_server.service.LocalGovernmentSupportService;
import com.kakaopay.platform.api_server.util.ResponseObject;
import com.kakaopay.platform.api_server.util.ResponseUtil;
import com.kakaopay.platform.api_server.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.util.List;


@RestController
@Api(value = "LocalGovernmentSupportController", description = "지자체 지원 정보 API")
public class LocalGovernmentSupportController {

    @Autowired
    LocalGovernmentSupportService localGovernmentSupportService;

    @GetMapping("local-government-support/v1")
    @ApiOperation(value = "지자체 지원정보 목록 조회", notes = "지자체 지원정보 전체 목록 조회 API", response = LocalGovernmentSupportResponse.class)
    public ResponseEntity<ResponseObject> getLocalGovernmentSupportList() {

        List<LocalGovernmentSupportResponse> localGovernmentSupportList = localGovernmentSupportService.getLocalGovernmentSupportList();

        return ResponseUtil.responseEntityAddOkData(localGovernmentSupportList);
    }

    @GetMapping("local-government-support/v1/search")
    @ApiOperation(value = "지자체명으로 검색", notes = "지자체명으로 지원하는 지자체 정보 검색 API", response = LocalGovernmentSupportResponse.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "region", value = "지자체명", required = true, paramType = "query", dataType = "String")
    })
    public ResponseEntity<ResponseObject> getLocalGovernmentSupport(
            @RequestParam("region") String region) {

        LocalGovernmentSupportResponse localGovernmentSupport = localGovernmentSupportService.getLocalGovernmentSupport(region);

        return ResponseUtil.responseEntityAddOkData(localGovernmentSupport);
    }

    @PostMapping("local-government-support/v1")
    @ApiOperation(value = "지자체 협약 지원 정보 등록", notes = "지자체 지원 정보 등록 API", response = LocalGovernmentSupportResponse.class)
    public ResponseEntity<ResponseObject> createLocalGovernmentSupport(@RequestBody LocalGovernmentSupportRequest localGovernmentSupportRequest) {

        if (StringUtils.isEmpty(localGovernmentSupportRequest.getRegion()) ||
                StringUtils.isEmpty(localGovernmentSupportRequest.getTarget()) ||
                StringUtils.isEmpty(localGovernmentSupportRequest.getUsage()) ||
                StringUtils.isEmpty(localGovernmentSupportRequest.getInstitute()) ||
                StringUtils.isEmpty(localGovernmentSupportRequest.getMgmt()) ||
                StringUtils.isEmpty(localGovernmentSupportRequest.getLimit()) ||
                StringUtils.isEmpty(localGovernmentSupportRequest.getRate()) ||
                StringUtils.isEmpty(localGovernmentSupportRequest.getReception())) {

            throw new NoParamsException();
        }
        LocalGovernmentSupportResponse localGovernmentSupport = localGovernmentSupportService.createLocalGovernmentSupport(localGovernmentSupportRequest);

        return ResponseUtil.responseEntityAddOkData(localGovernmentSupport);
    }

    @PutMapping(value = "local-government-support/v1/{id}")
    @ApiOperation(value = "지자체 협약 지원 정보 수정", notes = "지자체 지원 정보 수정 API", response = LocalGovernmentSupportResponse.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "지자체 지원정보  id", required = true, paramType = "path", dataType = "Long")
    })
    public ResponseEntity<ResponseObject> updateLocalGovernmentSupport(
            @PathVariable("id") Long id,
            @RequestBody LocalGovernmentSupportRequest localGovernmentSupportRequest) {

        LocalGovernmentSupportResponse localGovernmentSupport = localGovernmentSupportService.updateLocalGovernmentSupport(id, localGovernmentSupportRequest);

        return ResponseUtil.responseEntityAddOkData(localGovernmentSupport);
    }

    @GetMapping("local-government-support/v1/search/region")
    @ApiOperation(value = "지자체 협약 지원 정보 특정 개수 검색(지원금액 내림차순)", notes = "지자체 지원 정보 지원금액 내림차순으로 n개 검색 API")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit", value = "출력 개수", required = true, paramType = "query", dataType = "int")
    })
    public ResponseEntity<ResponseObject> getRegionOrderBySupportLimit(
            @RequestParam("limit") Integer limit
    ) {
        String regions = localGovernmentSupportService.getRegionOrderBySupportLimit(limit);

        return ResponseUtil.responseEntityAddOkData(regions);
    }

    @GetMapping("local-government-support/v1/search/institute")
    @ApiOperation(value = "보전 비율이 가장 작은 추천 기관명 조회", notes = "보전 비율이 가장 작은 추천 기관명 조회 API")
    public ResponseEntity<ResponseObject> getInstituteOrderByRate() {
        String institute = localGovernmentSupportService.getInstituteOrderByRate();

        return ResponseUtil.responseEntityAddOkData(institute);
    }

    @PostMapping("local-government-support/v1/upload")
    @ApiOperation(value = "지자체 협약 지원 정보 엑셀 업로드로 등록", notes = "지자체 협약 지원 정보 엑셀 업로드 API")
    public ResponseEntity<ResponseObject> readExcel(@RequestParam("file") MultipartFile multipartFile)
            throws IOException, InvalidFormatException {
        List<LocalGovernmentSupportRequest> localGovernmentSupportList = localGovernmentSupportService.uploadLocalGovernmentSupportFile(multipartFile);

        return ResponseUtil.responseEntityAddOkData(localGovernmentSupportList);
    }

//    @GetMapping("local-gorvernment-support/recommend")
//    @ApiOperation(value = "지자체 추천", notes = "지자체 추천 API")
//    public ResponseEntity<ResponseObject> recommendLocalGovernment() {
//
//    }

}
