package com.doping.admin.exception;

import lombok.Getter;

@Getter
public class DopingRuntimeException extends RuntimeException {
    private final String code;

    public DopingRuntimeException(String code, String message) {
        super(message);
        this.code = code;
    }


}
