package com.hh.record.config.exception.errorCode;

public class MailSendException extends CustomException{

    public MailSendException(String message) {
        super(message, ErrorCode.MAIL_SEND_EXCEPTION);
    }

}
