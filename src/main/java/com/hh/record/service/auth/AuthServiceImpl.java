package com.hh.record.service.auth;

import com.hh.record.dto.auth.AuthRequest;
import com.hh.record.dto.auth.response.GoogleAccessTokenResponse;
import com.hh.record.dto.auth.response.GoogleMemberInfoResponse;
import com.hh.record.entity.member.Member;
import com.hh.record.repository.member.MemberRepository;
import com.hh.record.security.util.JWTUtil;
import com.hh.record.service.auth.google.GoogleApiCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final GoogleApiCaller googleApiCaller;
    private final MemberRepository memberRepository;
    private final JWTUtil jwtUtil;

    @Override
    public String googleAuthentication(AuthRequest request) {
        GoogleAccessTokenResponse googleAccessTokenResponse = googleApiCaller.tokenAuthentication(request.getCode(), request.getRedirectUri());
        GoogleMemberInfoResponse googleMemberInfo = googleApiCaller.getGoogleMemberInfo(googleAccessTokenResponse.getAccessToken());
        Member member = memberRepository.findByEmail(googleMemberInfo.getEmail())
                .orElseGet(() -> memberRepository.save(Member.newMember(googleMemberInfo.getEmail())));
        return jwtUtil.generateToken(member.getEmail(), member.getRoleSet());
    }

}
