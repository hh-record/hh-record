package com.hh.record.dto.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GoogleAuthRequest {

    private String grantType;
    private String code;
    private String clientId;
    private String clientSecret;
    private String redirectUri;

    @Builder
    public GoogleAuthRequest(String grantType, String code, String clientId, String clientSecret, String redirectUri) {
        this.grantType = grantType;
        this.code = code;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
    }

    public static GoogleAuthRequest of(String grantType, String code, String clientId, String clientSecret, String redirectUri) {
        return new GoogleAuthRequest(grantType, code, clientId, clientSecret, redirectUri);
    }

}
