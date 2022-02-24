package com.hh.record.service.auth.google;

import com.hh.record.dto.auth.response.GoogleAccessTokenResponse;
import com.hh.record.dto.auth.response.GoogleMemberInfoResponse;

public interface GoogleApiCaller {

    GoogleAccessTokenResponse tokenAuthentication(String code, String redirectUri);

    GoogleMemberInfoResponse getGoogleMemberInfo(String accessToken);

}
