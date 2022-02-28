package com.hh.record.service.auth.google;

import com.hh.record.config.component.GoogleAuthComponent;
import com.hh.record.dto.auth.GoogleAuthRequest;
import com.hh.record.dto.auth.response.GoogleAccessTokenResponse;
import com.hh.record.dto.auth.response.GoogleMemberInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GoogleClient {

    private final GoogleApiCallerFeignClient googleApiCallerFeignClient;
    private final GoogleAuthComponent googleAuthComponent;

    public GoogleMemberInfoResponse googleAuth(String code, String redirectUri) {
        GoogleAuthRequest request = this.createRequest(code, redirectUri);
        GoogleAccessTokenResponse googleAccessTokenResponse = googleApiCallerFeignClient.tokenAuthentication(request);
        return googleApiCallerFeignClient.getGoogleMemberInfo(createBearer(googleAccessTokenResponse.getAccessToken()));
    }

    private GoogleAuthRequest createRequest(String code, String redirectUri) {
        return GoogleAuthRequest.builder()
                .grantType(googleAuthComponent.getGrantType())
                .code(code)
                .clientId(googleAuthComponent.getClientId())
                .clientSecret(googleAuthComponent.getClientSecret())
                .redirectUri(redirectUri)
                .build();
    }

    private String createBearer(String accessToken) {
        return String.format("Bearer %s", accessToken);
    }

}
