package com.hh.record.service;

import com.hh.record.entity.member.Member;
import com.hh.record.entity.member.MemberFollow;
import com.hh.record.entity.member.MemberProvider;
import com.hh.record.entity.member.MemberRole;
import com.hh.record.config.exception.errorCode.ValidationException;
import com.hh.record.dto.member.request.InsertMemberRequestDTO;
import com.hh.record.repository.MemberFollowRepository;
import com.hh.record.repository.member.MemberRepository;
import com.hh.record.service.member.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberFollowRepository memberFollowRepository;

    @AfterEach
    void cleanUp() {
        memberRepository.deleteAll();
    }

    @Test
    void memberSignUp() {
        // given
        InsertMemberRequestDTO request = InsertMemberRequestDTO.testInstance("admin1", "admin1", "test@test.com", "2234", "1111");

        // when
        memberService.insertMember(request);

        // then
        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList).hasSize(1);
        assertThat(memberList.get(0).getEmail()).isEqualTo(request.getEmail());
        assertThat(memberList.get(0).getUserName()).isEqualTo(request.getUserName());
    }

    @Test
    @DisplayName("이미 존재하는 아이디이면 예외처리")
    void memberSignUp2() {
        // given
        Member member = new Member("admin1", "admin1", "test@test.com", "1111", "1111", MemberRole.USER, MemberProvider.LOCAL);
        memberRepository.save(member);
        InsertMemberRequestDTO request = InsertMemberRequestDTO.testInstance("admin1", "admin1", "test@test.com", "2234", "1111");

        assertThatThrownBy(
                () -> memberService.insertMember(request)
        ).isInstanceOf(ValidationException.class);
    }

    @DisplayName("다른 누군가를 팔로우한다 (1이 2를 팔로우한다.)")
    @Test
    void memberFollow1() {
        // given
        Member member1 = new Member("admin1", "admin1", "test@test.com", "1111", "1111", MemberRole.USER, MemberProvider.LOCAL);
        Member member2 = new Member("admin1", "admin2", "test2@test.com", "1111", "1111", MemberRole.USER, MemberProvider.LOCAL);
        memberRepository.saveAll(Arrays.asList(member1, member2));

        // when
        memberService.followMember(member2.getSeq(), member1.getSeq());

        // then
        List<MemberFollow> memberFollowList = memberFollowRepository.findAll();
        assertThat(memberFollowList).hasSize(1);
    }

    @DisplayName("다른 누군가를 팔로우하는데 자기 자신이라면 예외처리")
    @Test
    void memberFollow2() {
        // given
        Member member1 = new Member("admin1", "admin1", "test@test.com", "1111", "1111", MemberRole.USER, MemberProvider.LOCAL);
        Member member2 = new Member("admin1", "admin2", "test2@test.com", "1111", "1111", MemberRole.USER, MemberProvider.LOCAL);
        memberRepository.saveAll(Arrays.asList(member1, member2));

        // when
        assertThatThrownBy(
                () -> memberService.followMember(member1.getSeq(), member1.getSeq())
        ).isInstanceOf(ValidationException.class);
    }

    @DisplayName("다른 누군가를 팔로우하는데 이미 팔로우한 회원이라면 예외처리")
    @Test
    void memberFollow3() {
        // given
        Member member1 = new Member("admin1", "admin1", "test@test.com", "1111", "1111", MemberRole.USER, MemberProvider.LOCAL);
        Member member2 = new Member("admin1", "admin2", "test2@test.com", "1111", "1111", MemberRole.USER, MemberProvider.LOCAL);
        member1.followMember(member2);
        memberRepository.saveAll(Arrays.asList(member1, member2));

        // when
        assertThatThrownBy(
                () -> memberService.followMember(member2.getSeq(), member1.getSeq())
        ).isInstanceOf(ValidationException.class);
    }

}