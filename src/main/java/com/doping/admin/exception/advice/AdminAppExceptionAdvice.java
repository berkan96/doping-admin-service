package com.doping.admin.exception.advice;

import com.doping.admin.exception.DopingRuntimeException;
import com.doping.admin.persistence.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class AdminAppExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DopingRuntimeException.class)
    public ResponseEntity<ErrorResponse> handleException(DopingRuntimeException exception) {

        commonLoggingError(exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder()
                .code(exception.getCode())
                .message(exception.getMessage())
                .build());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponse>> handleException(MethodArgumentNotValidException exception){

        commonLoggingError(exception);
        List<ErrorResponse> validations = exception.getBindingResult().getAllErrors().stream()
                .map(objectError -> ErrorResponse.builder()
                        .code(objectError.getCode())
                        .message(objectError.getDefaultMessage())
                        .build()).toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validations);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {

        commonLoggingError(e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null);
    }

    private void commonLoggingError(Exception exception) {
        log.error("Error was occured : {}", exception.getMessage());
    }
}
