package com.hh.record.config.feignClient.google;

import com.hh.record.config.exception.errorCode.ValidationException;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class GoogleApiDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        FeignException feignException = FeignException.errorStatus(methodKey, response);
        if (feignException.status() > 400 && feignException.status() < 500 ) {
            return new ValidationException("Google API 호출중에 예외가 발생했습니다.");
        }
        return new ValidationException("Google API 호출중에 애러가 발생했습니다.");
    }
}
