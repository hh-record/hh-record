package com.hh.record.dto.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthRequest {

    private String code;
    private String redirectUri;

    public AuthRequest(String code, String redirectUri) {
        this.code = code;
        this.redirectUri = redirectUri;
    }

}
