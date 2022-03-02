package com.hh.record.service;

import com.hh.record.config.component.GoogleAuthComponent;
import com.hh.record.dto.auth.AuthRequest;
import com.hh.record.dto.auth.GoogleAuthRequest;
import com.hh.record.dto.auth.response.GoogleAccessTokenResponse;
import com.hh.record.dto.auth.response.GoogleMemberInfoResponse;
import com.hh.record.entity.member.Member;
import com.hh.record.entity.member.MemberProvider;
import com.hh.record.repository.member.MemberRepository;
import com.hh.record.security.util.JWTUtil;
import com.hh.record.service.auth.AuthServiceImpl;
import com.hh.record.service.auth.google.GoogleApiCallerFeignClient;
import com.hh.record.service.auth.google.GoogleClient;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class AuthServiceTest {

    @Autowired
    private AuthServiceImpl authServiceImpl;

    @Autowired
    private GoogleClient googleClient;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private GoogleAuthComponent googleAuthComponent;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        googleClient = new GoogleClient(new MockGoogleClient(), googleAuthComponent);
        authServiceImpl = new AuthServiceImpl(memberRepository, jwtUtil, googleClient);
    }

    @AfterEach
    void clean() {
        memberRepository.deleteAll();
    }

    @DisplayName("구글 로그인")
    @Test
    void googleAuthentication() {
        // given
        AuthRequest request = AuthRequest.testInstance("code", "redirectUri", MemberProvider.GOOGLE);

        // when
        authServiceImpl.googleAuthentication(request);

        // then
        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList).hasSize(1);
    }

    public static class MockGoogleClient implements GoogleApiCallerFeignClient {

        @Override
        public GoogleAccessTokenResponse tokenAuthentication(GoogleAuthRequest request) {
            return GoogleAccessTokenResponse.testInstance("accessToken", "refreshToken", "expiresin", "idToken");
        }

        @Override
        public GoogleMemberInfoResponse getGoogleMemberInfo(String accessToken) {
            return GoogleMemberInfoResponse.testInstance("id", "email", "picture", "name");
        }

    }

}
