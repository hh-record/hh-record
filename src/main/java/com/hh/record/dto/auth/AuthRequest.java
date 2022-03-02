package com.hh.record.dto.auth;

import com.hh.record.entity.member.MemberProvider;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthRequest {

    private String code;
    private String redirectUri;
    private MemberProvider provider;

    public AuthRequest(String code, String redirectUri, MemberProvider provider) {
        this.code = code;
        this.redirectUri = redirectUri;
        this.provider = provider;
    }

    public static AuthRequest testInstance(String code, String redirectUri, MemberProvider provider) {
        return new AuthRequest(code, redirectUri, provider);
    }

}
