package com.hh.record.service.auth;

import com.hh.record.dto.auth.AuthRequest;
import com.hh.record.dto.auth.response.GoogleMemberInfoResponse;
import com.hh.record.entity.member.Member;
import com.hh.record.repository.member.MemberRepository;
import com.hh.record.security.util.JWTUtil;
import com.hh.record.service.auth.google.GoogleClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MemberRepository memberRepository;
    private final JWTUtil jwtUtil;
    private final GoogleClient googleClient;

    @Override
    public String googleAuthentication(AuthRequest request) {
        GoogleMemberInfoResponse googleMemberInfoResponse = googleClient.googleAuth(request.getCode(), request.getRedirectUri());
        Member member = memberRepository.findByEmail(googleMemberInfoResponse.getEmail())
                .orElseGet(() -> memberRepository.save(Member.newMember(googleMemberInfoResponse, request.getProvider())));
        return jwtUtil.generateToken(member.getEmail(), member.getRoleSet());
    }

}
