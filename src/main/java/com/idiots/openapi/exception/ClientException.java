package com.idiots.openapi.exception;

import com.idiots.openapi.utils.ApiUtils;
import org.springframework.http.HttpStatus;

public abstract class ClientException extends RuntimeException {
    public ClientException(String message) {
        super(message);
    }

    abstract ApiUtils.ApiResult<?> body();

    abstract HttpStatus status();
}
