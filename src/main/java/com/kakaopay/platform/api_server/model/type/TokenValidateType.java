package com.kakaopay.platform.api_server.model.type;

public enum TokenValidateType {

    TOKEN_NOT_FOUND("해당 액세스 토큰과 일치하는 회원이 없습니다."),
    REFRESH_TOKEN_NOT_FOUND("리프레쉬 토큰이 존재하지 않습니다."),
    EXPIRED_ACCESS_TOKEN("만료된 액세스 토큰입니다."),
    EXPIRED_REFRESH_TOKEN("만료된 리프레쉬 토큰입니다. 회원 재인증이 필요합니다."),
    WRONG_ACCESS_TOKEN("잘못된 액세스 토큰입니다."),
    BROKEN_ACCESS_TOKEN("변조된 액세스 토큰입니다."),
    BROKEN_REFRESH_TOKEN("변조된 리프레쉬 토큰입니다."),
    TOKEN_CHECK_EXCEPTION("토큰 검증 중 오류가 발생하였습니다.");

    private String value;

    TokenValidateType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
