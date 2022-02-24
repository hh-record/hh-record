package com.hh.record.dto.auth.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GoogleAccessTokenResponse {

    private String accessToken;

    private String refreshToken;

    private String expiresIn;

    private String idToken;

    public GoogleAccessTokenResponse(String accessToken, String refreshToken, String expiresIn, String idToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.idToken = idToken;
    }

}
