package com.hh.record.controller;

import com.hh.record.config.exception.errorCode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {

    private final int code;
    private final String message;
    private final String description;

    public static ErrorResponse error(ErrorCode errorCode, String description) {
        return new ErrorResponse(errorCode.getCode(), errorCode.getMessage(), description);
    }

}