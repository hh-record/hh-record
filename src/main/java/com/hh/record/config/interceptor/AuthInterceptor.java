package com.hh.record.config.interceptor;

import com.hh.record.config.exception.errorCode.NotFoundException;
import com.hh.record.config.exception.errorCode.ValidationException;
import com.hh.record.entity.member.Member;
import com.hh.record.repository.member.MemberRepository;
import com.hh.record.security.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final JWTUtil jwtUtil;
    private final MemberRepository memberRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // HandlerMethod 타입인지 체크
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

        // method @Auth 가 없는 경우, 즉 인증이 필요 없는 요청
        if (auth == null) {
            return true;
        }
        //claims 체크 false 가 나오면 애러 처리
        String token = request.getHeader("AUTH_TOKEN");
        log.info(String.format("token = %s", token));
        if (token == null || token.length() == 0) {
            throw new NotFoundException("토큰이 존재하지 않습니다.");
        }
        if (!jwtUtil.validateToken(token)) {
            throw new ValidationException("알맞지 않은 토큰입니다.");
        }
        String userId  = jwtUtil.getUserId(token);
        Member member    = memberRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("존재하지 않는 멤버 %s 입니다.", userId)));
        request.setAttribute("memberId", member.getSeq());
        return true;
    }

}
