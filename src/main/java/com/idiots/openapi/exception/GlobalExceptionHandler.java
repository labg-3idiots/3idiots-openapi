package com.idiots.openapi.exception;

import com.idiots.openapi.utils.ApiUtils;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ClientException.class})
    public ResponseEntity<?> clientException(ClientException exception) {
        return new ResponseEntity<>(exception.body(), exception.status());
    }

    @ExceptionHandler({ServerException.class})
    public ResponseEntity<?> serverException(ServerException exception) {
        return new ResponseEntity<>(exception.body(), exception.status());
    }
}
