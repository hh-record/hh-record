package com.hh.record.service.member;

import com.hh.record.config.exception.errorCode.ValidationException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MemberServiceUtils {

    public static void validatePassword(PasswordEncoder passwordEncoder, String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new ValidationException("비밀번호가 맞지 않습니다.");
        }
    }

    public static void validateSamePassword(PasswordEncoder passwordEncoder, String rawPassword, String encodedPassword) {
        if (passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new ValidationException("이전 비밀번호와 같습니다.");
        }
    }

}
