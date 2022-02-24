package com.hh.record.config.exception.errorCode;

public class ValidationException extends CustomException {

    public ValidationException(String message) {
        super(message, ErrorCode.VALIDATION_EXCEPTION);
    }

}
