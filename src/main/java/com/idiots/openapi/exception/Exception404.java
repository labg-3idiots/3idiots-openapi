package com.idiots.openapi.exception;

import com.idiots.openapi.utils.ApiUtils;
import org.springframework.http.HttpStatus;

public class Exception404 extends ClientException {
    private final BaseExceptionStatus exceptionStatus;

    public Exception404(BaseExceptionStatus exception) {
        super(exception.getMessage());
        exceptionStatus = exception;
    }

    @Override
    public ApiUtils.ApiResult<?> body() {
        return ApiUtils.error(getMessage(), exceptionStatus.getStatus(), exceptionStatus.getErrorCode());
    }

    @Override
    public HttpStatus status() {
        return HttpStatus.NOT_FOUND;
    }
}
