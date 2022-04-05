package com.hh.record.service.social;

import com.hh.record.dto.member.response.RecommendFriendsDTO;
import com.hh.record.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SocialServiceImpl implements SocialService {

    private final MemberRepository memberRepository;

    @Override
    public List<RecommendFriendsDTO> recommendFriends(Long memberId) {
        return memberRepository.selectRecommendFriends(memberId);
    }
}
