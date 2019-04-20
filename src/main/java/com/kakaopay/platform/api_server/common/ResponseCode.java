package com.kakaopay.platform.api_server.common;

import lombok.Getter;

public enum ResponseCode {

    SUCCESS("0000", "알림", "정상처리"),


    ERROR_UNEXPECTED("3001", "", "오류가 발생했습니다. 다시 시도해 주세요."),
    ERROR_LOCAL_GOVERNMENT_NAME_DUPLICATION("3002", "", "이미 등록된 지자체입니다. 지자체명 확인 후 다시 시도해 주세요."),
    ERROR_NOT_FOUNT_LOCAL_GOVERNMENT_SUPPORT("3003", "", "해당하는 지자체 지원정보가 없습니다."),
    ERROR_LOCAL_GOVERNMENT_SUPPORT_DUPLICATION("3004", "", "해당 지자체 지원 정보가 이미 등록되어 있습니다. 확인 후 다시 시도해 주세요."),
    ERROR_FILE_UPLOAD_PROCESS("3005", "", "파일 업로드 중 오류가 발생했습니다. (.xlsx/ .xls)파일만 가능합니다. 확인 후 다시 시도해 주세요."),

    ERROR_REQUEST_PARAMS("4001", "", "오류가 발생했습니다. 입력값을 확인해 주세요."),

    ERROR_USER_DUPLICATION("5001", "", "이미 등록된 아이디입니다. 다시 시도해 주세요.");

    @Getter
    private String code;

    @Getter
    private String title;

    @Getter
    private String message;

    ResponseCode(String code, String title, String message) {
        this.code = code;
        this.title = title;
        this.message = message;
    }
}
