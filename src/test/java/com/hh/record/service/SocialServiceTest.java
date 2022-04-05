package com.hh.record.service;

import com.hh.record.dto.member.response.RecommendFriendsDTO;
import com.hh.record.entity.member.Member;
import com.hh.record.entity.member.MemberProvider;
import com.hh.record.entity.member.MemberRole;
import com.hh.record.repository.member.MemberRepository;
import com.hh.record.service.social.SocialService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


@SpringBootTest
public class SocialServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private SocialService socialService;

    @AfterEach
    void cleanUp() {
        memberRepository.deleteAll();
    }

    @DisplayName("추천 친구가 조회되는지, 내가 팔로우한 사람이 제외되는지")
    @Test
    void recommendFriends() {
        // given
        List<Member> members = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            members.add(new Member("admin" + i, "admin" + i, "test" + i + "@test.com", "1111", "1111", false, "test" + i + ".com", MemberRole.USER, MemberProvider.LOCAL));
        }

        members.get(0).followMember(members.get(1));
        members.get(0).followMember(members.get(2));
        members.get(0).followMember(members.get(3));
        members.get(0).followMember(members.get(4));

        memberRepository.saveAll(members);

        // when
        List<RecommendFriendsDTO> results = socialService.recommendFriends(members.get(0).getSeq());

        // then
        assertThat(results.size()).isEqualTo(4);
        for (RecommendFriendsDTO result : results) {
            assertNotEquals(result.getSeq(), members.get(0).getSeq());
            assertNotEquals(result.getSeq(), members.get(1).getSeq());
            assertNotEquals(result.getSeq(), members.get(2).getSeq());
            assertNotEquals(result.getSeq(), members.get(3).getSeq());
            assertNotEquals(result.getSeq(), members.get(4).getSeq());
        }

    }

}
