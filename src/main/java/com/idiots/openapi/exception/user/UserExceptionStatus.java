package com.idiots.openapi.exception.user;

import com.idiots.openapi.exception.BaseExceptionStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserExceptionStatus implements BaseExceptionStatus  {
    USER_NOT_FOUND(404, "회원이 존재하지 않습니다", "10404"),
    USER_ALREADY_DELETED(400, "이미 삭제된 회원입니다.", "10400");

    private final int status;
    private final String message;
    private final String errorCode;
}