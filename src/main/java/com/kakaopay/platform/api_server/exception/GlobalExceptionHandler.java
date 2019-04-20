package com.kakaopay.platform.api_server.exception;

import com.kakaopay.platform.api_server.common.ResponseCode;
import com.kakaopay.platform.api_server.util.ResponseObject;
import com.kakaopay.platform.api_server.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static String code;
    private static String title;
    private static String message;

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ResponseObject> handleException(HttpServletRequest request, Exception e) {
        log.error(String.valueOf(request.getRequestURL()));

        code = ResponseCode.ERROR_UNEXPECTED.getCode();
        title = ResponseCode.ERROR_UNEXPECTED.getTitle();
        message = ResponseCode.ERROR_UNEXPECTED.getMessage();

        return ResponseUtil.responseEntityAddOk(code, title, message);
    }

    /**
     * 지자체 (기관) 중복 등록 오류
     *
     * @param request
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = LocalGovernmentDuplicationException.class)
    public ResponseEntity<ResponseObject> handleLocalGovernmentDuplicationException(HttpServletRequest request, LocalGovernmentDuplicationException e) {
        log.info(String.valueOf(request.getRequestURL()));

        code = ResponseCode.ERROR_LOCAL_GOVERNMENT_NAME_DUPLICATION.getCode();
        title = ResponseCode.ERROR_LOCAL_GOVERNMENT_NAME_DUPLICATION.getTitle();
        message = ResponseCode.ERROR_LOCAL_GOVERNMENT_NAME_DUPLICATION.getMessage();

        return ResponseUtil.responseEntityAddOk(code, title, message);
    }

    /**
     * 지자체 지원 정보 중복 등록 오류
     * @param request
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = LocalGovernmentSupportDuplicationException.class)
    public ResponseEntity<ResponseObject> handleLocalGovernmentSupportDuplicationException(HttpServletRequest request, LocalGovernmentSupportDuplicationException e) {
        log.info(String.valueOf(request.getRequestURL()));

        code = ResponseCode.ERROR_LOCAL_GOVERNMENT_SUPPORT_DUPLICATION.getCode();
        title = ResponseCode.ERROR_LOCAL_GOVERNMENT_SUPPORT_DUPLICATION.getTitle();
        message = ResponseCode.ERROR_LOCAL_GOVERNMENT_SUPPORT_DUPLICATION.getMessage();

        return ResponseUtil.responseEntityAddOk(code, title, message);
    }


    /**
     * 지자체 지원정보 검색 결과 없음
     *
     * @param request
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = NotFoundLocalGovernmentSupportException.class)
    public ResponseEntity<ResponseObject> handleNotFoundLocalGovernmentSupportException(HttpServletRequest request, NotFoundLocalGovernmentSupportException e) {
        log.info(String.valueOf(request.getRequestURL()));

        code = ResponseCode.ERROR_NOT_FOUNT_LOCAL_GOVERNMENT_SUPPORT.getCode();
        title = ResponseCode.ERROR_NOT_FOUNT_LOCAL_GOVERNMENT_SUPPORT.getTitle();
        message = ResponseCode.ERROR_NOT_FOUNT_LOCAL_GOVERNMENT_SUPPORT.getMessage();

        return ResponseUtil.responseEntityAddOk(code, title, message);
    }

    /**
     * request param 오류
     *
     * @param request
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = NoParamsException.class)
    public ResponseEntity<ResponseObject> handleNoParamsException(HttpServletRequest request, NoParamsException e) {
        log.info(String.valueOf(request.getRequestURL()));

        code = ResponseCode.ERROR_REQUEST_PARAMS.getCode();
        title = ResponseCode.ERROR_REQUEST_PARAMS.getTitle();
        message = ResponseCode.ERROR_REQUEST_PARAMS.getMessage();

        return ResponseUtil.responseEntityAddOk(code, title, message);
    }

    /**
     * file upload 오류
     *
     * @param request
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = FileUploadException.class)
    public ResponseEntity<ResponseObject> handleFileUploadException(HttpServletRequest request, FileUploadException e) {
        log.info(String.valueOf(request.getRequestURL()));

        code = ResponseCode.ERROR_FILE_UPLOAD_PROCESS.getCode();
        title = ResponseCode.ERROR_FILE_UPLOAD_PROCESS.getTitle();
        message = ResponseCode.ERROR_FILE_UPLOAD_PROCESS.getMessage();

        return ResponseUtil.responseEntityAddOk(code, title, message);
    }

    /**
     * 유저 중복 등록 오류
     *
     * @param request
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = UserDuplicationException.class)
    public ResponseEntity<ResponseObject> handleUserDuplicationException(HttpServletRequest request, UserDuplicationException e) {
        log.info(String.valueOf(request.getRequestURL()));

        code = ResponseCode.ERROR_USER_DUPLICATION.getCode();
        title = ResponseCode.ERROR_USER_DUPLICATION.getTitle();
        message = ResponseCode.ERROR_USER_DUPLICATION.getMessage();

        return ResponseUtil.responseEntityAddOk(code, title, message);
    }
}
