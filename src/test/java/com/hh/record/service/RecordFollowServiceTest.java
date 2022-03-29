package com.hh.record.service;

import com.hh.record.config.exception.errorCode.NotFoundException;
import com.hh.record.config.exception.errorCode.ValidationException;
import com.hh.record.dto.record.RecordWithThemResponseDto;
import com.hh.record.entity.Theme;
import com.hh.record.entity.member.Member;
import com.hh.record.entity.member.MemberProvider;
import com.hh.record.entity.member.MemberRole;
import com.hh.record.entity.record.IsPrivate;
import com.hh.record.entity.record.Record;
import com.hh.record.repository.MemberFollowRepository;
import com.hh.record.repository.member.MemberRepository;
import com.hh.record.repository.record.RecordRepository;
import com.hh.record.repository.theme.ThemeRepository;
import com.hh.record.service.record.RecordFollowService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class RecordFollowServiceTest {

    @Autowired
    private RecordFollowService recordFollowService;

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private MemberFollowRepository memberFollowRepository;

    @Autowired
    private MemberRepository memberRepository;

    @AfterEach
    public void clean() {
        recordRepository.deleteAll();
        themeRepository.deleteAll();
        memberFollowRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @DisplayName("1번이 2번을 팔로우 한 상태에서 1번이 2번의 게시글을 보려고 한다. 게시글의 theme 이 N일 경우")
    @Test
    void selectOneRecord() {
        // given
        Member member1 = this.createMember("a1", "asd2@naver.com");
        Member member2 = this.createMember("aa2", "sdf@naver.com");
        member1.followMember(member2);
        memberRepository.saveAll(Arrays.asList(member1, member2));

        Record record = new Record(member2, "sss", "title1", "content1", IsPrivate.ALL_PUBLIC, "N");
        recordRepository.save(record);

        // when
        RecordWithThemResponseDto recordWithThemResponseDto = recordFollowService.selectOneRecord(member1.getSeq(), record.getSeq());

        // then
        assertThat(recordWithThemResponseDto.getRecord().getTitle()).isEqualTo(record.getTitle());
        assertThat(recordWithThemResponseDto.getTheme()).isNull();
    }

    @DisplayName("1번이 2번을 팔로우 한 상태에서 1번이 2번의 게시글을 보려고 한다. 게시글의 theme 이 Y일 경우")
    @Test
    void selectOneRecord2() {
        // given
        Member member1 = this.createMember("a1", "asd2@naver.com");
        Member member2 = this.createMember("aa2", "sdf@naver.com");
        member1.followMember(member2);
        memberRepository.saveAll(Arrays.asList(member1, member2));

        Record record = new Record(member2, "sss", "title1", "content1", IsPrivate.ALL_PUBLIC, "Y");
        recordRepository.save(record);
        Theme theme = new Theme(null, "오늘의 주제는 ~~~", LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        themeRepository.save(theme);

        // when
        RecordWithThemResponseDto recordWithThemResponseDto = recordFollowService.selectOneRecord(member1.getSeq(), record.getSeq());

        // then
        assertThat(recordWithThemResponseDto.getRecord().getTitle()).isEqualTo(record.getTitle());
        assertThat(recordWithThemResponseDto.getTheme().getContent()).isEqualTo(theme.getContent());
    }

    @DisplayName("1번이 2번을 팔로우 한 상태에서 1번이 2번의 게시글을 보려고 한다. 게시글의 theme 이 Y 이지만 해당 날짜의 theme 이 없다면 예외처리")
    @Test
    void selectOneRecord3() {
        // given
        Member member1 = this.createMember("a1", "asd2@naver.com");
        Member member2 = this.createMember("aa2", "sdf@naver.com");
        member1.followMember(member2);
        memberRepository.saveAll(Arrays.asList(member1, member2));

        Record record = new Record(member2, "sss", "title1", "content1", IsPrivate.ALL_PUBLIC, "Y");
        recordRepository.save(record);

        assertThatThrownBy(
                () -> recordFollowService.selectOneRecord(member1.getSeq(), record.getSeq())
        ).isInstanceOf(NotFoundException.class);
    }

    @DisplayName("만약 팔로우 하지 않은 상대의 게시글에 접근하려하는 경우 예외처리")
    @Test
    void selectOneRecord4() {
        // given
        Member member1 = this.createMember("a1", "asd2@naver.com");
        Member member2 = this.createMember("aa2", "sdf@naver.com");
        memberRepository.saveAll(Arrays.asList(member1, member2));

        Record record = new Record(member2, "sss", "title1", "content1", IsPrivate.ALL_PUBLIC, "Y");
        recordRepository.save(record);

        assertThatThrownBy(
                () -> recordFollowService.selectOneRecord(member1.getSeq(), record.getSeq())
        ).isInstanceOf(ValidationException.class);
    }

    private Member createMember(String id, String email) {
        return Member.builder()
                .id(id)
                .email(email)
                .isPrivate(Boolean.TRUE)
                .password("sdf")
                .phoneNumber("0101010")
                .profileImgUrl("sdf")
                .roleSet(MemberRole.USER)
                .provider(MemberProvider.LOCAL)
                .build();
    }

}
