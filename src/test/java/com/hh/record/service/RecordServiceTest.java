package com.hh.record.service;

import com.hh.record.dto.record.CreateRecordRequestDto;
import com.hh.record.dto.record.RecordResponseDTO;
import com.hh.record.dto.record.RecordSearchRequestDTO;
import com.hh.record.entity.member.Member;
import com.hh.record.entity.member.MemberProvider;
import com.hh.record.entity.member.MemberRole;
import com.hh.record.entity.Record;
import com.hh.record.repository.member.MemberRepository;
import com.hh.record.repository.record.RecordRepository;
import com.hh.record.service.record.RecordService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class RecordServiceTest {

    @Autowired
    private RecordService recordService;

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private MemberRepository memberRepository;

    @AfterEach
    void clean() {
        recordRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    void retrieveRecord() {
        // given
        Member member = new Member("admin1", "admin1", "test@test.com", "1111", "1111", MemberRole.USER, MemberProvider.LOCAL);
        memberRepository.save(member);

        Record record1 = new Record(member, "sss", "title1", "content1");
        Record record2 = new Record(member, "sss", "title2", "content2");
        recordRepository.saveAll(Arrays.asList(record1, record2));

        RecordSearchRequestDTO requestDTO = new RecordSearchRequestDTO(null, null);

        // when
        List<RecordResponseDTO> responseDTOList = recordService.retrieveRecord(member.getSeq(), requestDTO);

        // then
        assertThat(responseDTOList).hasSize(2);
    }

    @Test
    void createRecord() {
        // given
        Member member = new Member("admin1", "admin1", "test@test.com", "1111", "1111", MemberRole.USER, MemberProvider.LOCAL);
        memberRepository.save(member);

        CreateRecordRequestDto requestDto = new CreateRecordRequestDto("thumbnailUrl", "title", "content", "fileKey");

        // when
        recordService.createRecord(member.getSeq(), requestDto);

        // then
        List<Record> recordList = recordRepository.findAll();
        assertThat(recordList.get(0).getContent()).isEqualTo(requestDto.getContent());
        assertThat(recordList.get(0).getTitle()).isEqualTo(requestDto.getTitle());
    }

}
