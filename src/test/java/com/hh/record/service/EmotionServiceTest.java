package com.hh.record.service;

import com.hh.record.config.exception.errorCode.NotFoundException;
import com.hh.record.dto.record.EmotionRequestDTO;
import com.hh.record.dto.record.EmotionResponseDTO;
import com.hh.record.entity.member.Member;
import com.hh.record.entity.member.MemberProvider;
import com.hh.record.entity.member.MemberRole;
import com.hh.record.entity.record.Emotion;
import com.hh.record.entity.record.IsPrivate;
import com.hh.record.entity.record.Record;
import com.hh.record.entity.record.RecordEmotion;
import com.hh.record.repository.emotion.RecordEmotionRepository;
import com.hh.record.repository.member.MemberRepository;
import com.hh.record.repository.record.RecordRepository;
import com.hh.record.service.record.RecordEmotionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class EmotionServiceTest {

    @Autowired
    private RecordEmotionService emotionService;

    @Autowired
    private RecordEmotionRepository emotionRepository;

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private MemberRepository memberRepository;

    @AfterEach
    void cleanUp() {
        emotionRepository.deleteAll();
        memberRepository.deleteAll();
        recordRepository.deleteAll();
    }

    @Transactional
    @Test
    void insertEmotion() {
        // given
        Member member1 = new Member("admin1", "admin1", "test1@test.com", "1111", "1111", false, "test1.com", MemberRole.USER, MemberProvider.LOCAL);
        Member member2 = new Member("admin2", "admin2", "test2@test.com", "1111", "1111", true, "test2.com", MemberRole.USER, MemberProvider.LOCAL);
        memberRepository.save(member1);
        memberRepository.save(member2);

        Record record = new Record(member1, "test1.com", "title1", "content1", IsPrivate.FRIEND_PUBLIC, "Y");
        recordRepository.save(record);

        EmotionRequestDTO requestDTO = new EmotionRequestDTO(record.getSeq(), Emotion.LIKE);

        // when
        emotionService.insertEmotion(member2.getSeq(), requestDTO);

        // then
        List<EmotionResponseDTO> emotionList = emotionService.findByRecordEmotion(record.getSeq());
        assertThat(emotionList).hasSize(1);
        assertThat(emotionList.get(0).getRecordSeq()).isEqualTo(record.getSeq());
        assertThat(emotionList.get(0).getMemberSeq()).isEqualTo(member2.getSeq());
    }

    @Transactional
    @Test
    void deleteEmotion() {
        // given
        Member member1 = new Member("admin1", "admin1", "test1@test.com", "1111", "1111", false, "test1.com", MemberRole.USER, MemberProvider.LOCAL);
        Member member2 = new Member("admin2", "admin2", "test2@test.com", "1111", "1111", true, "test2.com", MemberRole.USER, MemberProvider.LOCAL);
        memberRepository.save(member1);
        memberRepository.save(member2);

        Record record = new Record(member1, "test1.com", "title1", "content1", IsPrivate.FRIEND_PUBLIC, "Y");
        recordRepository.save(record);

        RecordEmotion emotion = new RecordEmotion(record, member2, Emotion.LIKE);
        emotionRepository.save(emotion);

        // when
        emotionService.deleteEmotion(member2.getSeq(), record.getSeq());

        // then
        List<EmotionResponseDTO> emotionList = emotionService.findByRecordEmotion(record.getSeq());
        assertThat(emotionList).hasSize(0);
    }

    @DisplayName("일기, 멤버가 존재하지 않을때 ")
    @Transactional
    @Test
    void emotionTest1() {
        // given
        EmotionRequestDTO requestDTO = new EmotionRequestDTO(1L, Emotion.LIKE);

        // when & then
        assertThatThrownBy(
                () -> emotionService.insertEmotion(1L, requestDTO)
        ).isInstanceOf(NotFoundException.class);
    }

}
