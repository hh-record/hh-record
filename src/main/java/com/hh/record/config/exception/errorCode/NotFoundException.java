package com.hh.record.config.exception.errorCode;

public class NotFoundException extends CustomException {

    public NotFoundException(String message) {
        super(message, ErrorCode.NOT_FOUND_EXCEPTION);
    }

}
