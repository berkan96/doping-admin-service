package com.doping.admin.exception;

import com.doping.admin.constants.ErrorCodes;

public class NoSuchStudentException extends DopingRuntimeException {
    public NoSuchStudentException() {
        super(ErrorCodes.NO_SUCH_STUDENT_ERROR_CODE, "No such student...");
    }
}
