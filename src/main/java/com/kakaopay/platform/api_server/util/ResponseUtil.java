package com.kakaopay.platform.api_server.util;

import com.kakaopay.platform.api_server.common.ResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
public class ResponseUtil {

    public static ResponseEntity<ResponseObject> responseEntityAddOk(String code, String title, String message) {
        return new ResponseEntity<>(setResponseObject(code, title, message, null), HttpStatus.OK);
    }

    public static ResponseEntity<ResponseObject> responseEntityAddOk() {
        return new ResponseEntity<>(setResponseObject(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getTitle(), ResponseCode.SUCCESS.getMessage(), null), HttpStatus.OK);
    }

    public static ResponseEntity<ResponseObject> responseEntityAddOkData(Object data) {
        return new ResponseEntity<>(setResponseObject(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getTitle(), ResponseCode.SUCCESS.getMessage(), data), HttpStatus.OK);
    }

    public static ResponseEntity<ResponseObject> responseEntityAddOkData(String code, String title, String message, Object data) {
        return new ResponseEntity<>(setResponseObject(code, title, message, data), HttpStatus.OK);
    }

    public static ResponseEntity<ResponseObject> responseEntityAddConflict(String code, String title, String message) {
        return new ResponseEntity<>(setResponseObject(code, title, message, null), HttpStatus.CONFLICT);
    }

    public static ResponseEntity<ResponseObject> responseEntityAddConflictData(String code, String title, String message, Object data) {
        return new ResponseEntity<>(setResponseObject(code, title, message, data), HttpStatus.CONFLICT);
    }

    private static ResponseObject setResponseObject(String code, String title, String message, Object data) {
        ResponseObject responseObject = new ResponseObject();
        if (!StringUtils.isEmpty(code)) responseObject.setCode(code);
        if (!StringUtils.isEmpty(title)) responseObject.setTitle(title);
        if (!StringUtils.isEmpty(message)) responseObject.setMessage(message);
        if (!Objects.isNull(data)) responseObject.setData(data);

        return responseObject;
    }
}
