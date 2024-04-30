package com.doping.admin.exception;

import com.doping.admin.constants.ErrorCodes;

public class NoSuchExamException extends DopingRuntimeException {
    public NoSuchExamException() {
        super(ErrorCodes.NO_SUCH_EXAM_ERROR_CODE, "No such the Exam...");
    }
}
