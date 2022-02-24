package com.hh.record.config.interceptor;

import com.hh.record.config.exception.errorCode.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
@Component
public class MemberIdArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasAnnotation = parameter.getParameterAnnotation(MemberId.class) != null;
        boolean isMatchType = parameter.getParameterType().equals(Long.class);
        if (hasAnnotation && parameter.getMethodAnnotation(Auth.class) == null) {
            throw new ValidationException("어노테이션이 없거나 타입이 맞지 않습니다.");
        }
        return hasAnnotation && isMatchType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return webRequest.getAttribute("memberId", 0);
    }

}
