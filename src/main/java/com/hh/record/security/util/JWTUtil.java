package com.hh.record.security.util;

import com.hh.record.entity.member.MemberRole;
import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.util.Date;

@Log4j2
@Component
public class JWTUtil {

    private final static String SECRET_KEY = "hhRecordSecretKey";

    private final static long JWT_TOKEN_VALIDITY = 1000L * 60 * 60 * 24 * 365;

    // 토큰 정보 생성
    public String generateToken(String id, MemberRole roleSet) {
        Claims claims = Jwts.claims().setSubject(id);
        claims.put("roles", roleSet);

        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(JWT_TOKEN_VALIDITY).toInstant()))
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

    }

    // 토큰에서 회원 정보 추출
    public String getUserId(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 token 값을 가져옵니다. "AUTH-TOKEN" : "TOKEN 값"
    public String resolveToken(HttpServletRequest request) {
        String token = null;
        Cookie cookie = WebUtils.getCookie(request, "AUTH-TOKEN");
        if(cookie != null) token = cookie.getValue();
        return token;
    }

    // 토큰 유효성 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtToken).getBody();
            return true;
        } catch (MalformedJwtException e) {
            log.info("----------------- 잘못된 JWT 서명입니다. -----------------");
        } catch (ExpiredJwtException e) {
            log.info("----------------- 만료된 JWT 토큰입니다. -----------------");
        } catch (UnsupportedJwtException e) {
            log.info("-------------- 지원되지 않는 JWT 토큰입니다. --------------");
        } catch (IllegalArgumentException e) {
            log.info("--------------- JWT 토큰이 잘못되었습니다. ----------------");
        } catch (SignatureException e) {
            log.info("----------------- 잘못된 JWT 서명입니다. -----------------");
        }
        return false;
    }

}
