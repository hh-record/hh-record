package com.hh.record.service;

import com.hh.record.entity.Member;
import com.hh.record.entity.MemberRole;
import com.hh.record.config.exception.errorCode.ValidationException;
import com.hh.record.dto.member.request.InsertMemberRequestDTO;
import com.hh.record.repository.member.MemberRepository;
import com.hh.record.service.member.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

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
        Member member = new Member(0L, "admin1", "admin1", "test@test.com", "1111", "01011112222",Collections.singletonList(MemberRole.USER));
        memberRepository.save(member);
        InsertMemberRequestDTO request = InsertMemberRequestDTO.testInstance("admin1", "admin1", "test@test.com", "2234", "1111");

        assertThatThrownBy(
                () -> memberService.insertMember(request)
        ).isInstanceOf(ValidationException.class);
    }

}