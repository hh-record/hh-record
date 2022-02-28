package com.hh.record.service.auth.google;

import com.hh.record.config.feignClient.FeignClientConfig;
import com.hh.record.dto.auth.GoogleAuthRequest;
import com.hh.record.dto.auth.response.GoogleAccessTokenResponse;
import com.hh.record.dto.auth.response.GoogleMemberInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "googleApiCallerFeignClient",
        url = "${google.auth.base_url}",
        configuration = FeignClientConfig.class
)
public interface GoogleApiCallerFeignClient {

    @PostMapping("${google.auth.token_url}")
    GoogleAccessTokenResponse tokenAuthentication(@RequestBody GoogleAuthRequest request);

    @GetMapping("${google.auth.profile_url}")
    GoogleMemberInfoResponse getGoogleMemberInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken);

}
