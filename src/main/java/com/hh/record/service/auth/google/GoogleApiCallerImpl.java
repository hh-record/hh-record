package com.hh.record.service.auth.google;

import com.hh.record.config.component.GoogleAuthComponent;
import com.hh.record.config.exception.errorCode.ValidationException;
import com.hh.record.dto.auth.GoogleAuthRequest;
import com.hh.record.dto.auth.response.GoogleAccessTokenResponse;
import com.hh.record.dto.auth.response.GoogleMemberInfoResponse;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GoogleApiCallerImpl implements GoogleApiCaller{

    private final GoogleAuthComponent googleAuthComponent;
    private final WebClient webClient;

    @Override
    public GoogleAccessTokenResponse tokenAuthentication(String code, String redirectUri) {
        GoogleAuthRequest request = googleAccessTokenRequest(code, redirectUri);
        return webClient.post()
                .uri(googleAuthComponent.getUrl())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(request)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new ValidationException(String.format("잘못된 입력이 들어왔습니다 (%s) (%s)", code, redirectUri))))
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new ValidationException("구글 토큰 가져오는 중 에러가 발생하였습니다")))
                .bodyToMono(GoogleAccessTokenResponse.class)
                .block();
    }

    @Override
    public GoogleMemberInfoResponse getGoogleMemberInfo(String accessToken) {
        return webClient.get()
                .uri(googleAuthComponent.getUserInfoUrl())
                .headers(headers -> headers.setBearerAuth(accessToken))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new ValidationException(String.format("잘못된 토큰이 들어왔습니다 (%s)", accessToken))))
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new ValidationException("구글 유저 프로필 정보를 가져오는 중 에러가 발생하였습니다")))
                .bodyToMono(GoogleMemberInfoResponse.class)
                .block();
    }

    private GoogleAuthRequest googleAccessTokenRequest(String code, String redirectUri) {
        return GoogleAuthRequest.builder()
                .grantType(googleAuthComponent.getGrantType())
                .code(code)
                .clientId(googleAuthComponent.getClientId())
                .clientSecret(googleAuthComponent.getClientSecret())
                .redirectUri(redirectUri)
                .build();
    }

}
