package com.idiots.openapi.exception;

public interface BaseExceptionStatus {
    int getStatus();
    String getMessage();
    String getErrorCode();
}
