package com.idiots.openapi.exception.user;

import com.idiots.openapi.exception.BaseExceptionStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserExceptionStatus implements BaseExceptionStatus  {
    USER_NOT_FOUND(404, "회원이 존재하지 않습니다", "10404"),
    USER_ALREADY_EXIST(400, "이미 존재하는 회원입니다.", "10400"),
    USER_ALREADY_DELETED(400, "이미 삭제된 회원입니다.", "10400"),
    USER_SAVE_ERROR(500, "회원가입 실패하였습니다.", "10500"),
    ID_OR_PASSWORD_NULL(403, "아이디 또는 비밀번호가 빈 값입니다.", "10403"),
    PHONE_NUMBER_ALREADY_EXIST(400, "이미 가입된 핸드폰 번호입니다.", "10400");

    private final int status;
    private final String message;
    private final String errorCode;
}
