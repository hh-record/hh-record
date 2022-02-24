package com.hh.record.config.exception;

import com.hh.record.config.exception.errorCode.ErrorCode;
import com.hh.record.config.exception.errorCode.MailSendException;
import com.hh.record.config.exception.errorCode.NotFoundException;
import com.hh.record.config.exception.errorCode.ValidationException;
import com.hh.record.controller.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse handlerNotFound(NotFoundException e) {
        log.error(e.getMessage(), e);
        return ErrorResponse.error(e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ErrorResponse handlerValidation(ValidationException e) {
        log.error(e.getMessage(), e);
        return ErrorResponse.error(e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handlerMethodArgumentNotValid(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        log.error("e-----------------------" + e.getBindingResult());
        return ErrorResponse.error(ErrorCode.VALIDATION_EXCEPTION, e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse handlerIllegalArgumentException(IllegalArgumentException e) {
        log.error(e.getMessage(), e);
        log.error("e-----------------------" + e.getMessage());
        return ErrorResponse.error(ErrorCode.JWT_UNAUTHORIZED_EXCEPTION, e.getMessage());
    }

    @ExceptionHandler(MailSendException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse handlerMailSend(MailSendException e) {
        log.error(e.getMessage(), e);
        log.error("e-----------------------" + e.getMessage());
        return ErrorResponse.error(e.getErrorCode(), e.getMessage());
    }

}
