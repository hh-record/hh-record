package com.hh.record.service;

import com.hh.record.dto.record.CreateRecordRequestDto;
import com.hh.record.dto.record.RecordResponseDTO;
import com.hh.record.dto.record.RecordSearchRequestDTO;
import com.hh.record.entity.File;
import com.hh.record.entity.record.RecordHashTag;
import com.hh.record.entity.member.Member;
import com.hh.record.entity.member.MemberProvider;
import com.hh.record.entity.member.MemberRole;
import com.hh.record.entity.record.Record;
import com.hh.record.repository.FileRepository;
import com.hh.record.repository.RecordHashTagRepository;
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

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private RecordHashTagRepository recordHashTagRepository;

    @AfterEach
    void clean() {
        recordRepository.deleteAll();
        memberRepository.deleteAll();
        fileRepository.deleteAll();
        recordHashTagRepository.deleteAll();
    }

    @Test
    void retrieveRecord() {
        // given
        Member member = new Member("admin1", "admin1", "test@test.com", "1111", "1111", false, "test.com", MemberRole.USER, MemberProvider.LOCAL);
        memberRepository.save(member);

        Record record1 = new Record(member, "sss", "title1", "content1", Boolean.TRUE, "N");
        Record record2 = new Record(member, "sss", "title2", "content2", Boolean.TRUE, "N");
        record2.addFile(Arrays.asList("c", "d"));
        recordRepository.saveAll(Arrays.asList(record1, record2));

        RecordSearchRequestDTO requestDTO = new RecordSearchRequestDTO(null, null, null);

        // when
        List<RecordResponseDTO> responseDTOList = recordService.retrieveRecord(member.getSeq(), requestDTO);

        // then
        assertThat(responseDTOList).hasSize(2);
    }

    @Test
    void createRecord() {
        // given
        Member member = new Member("admin1", "admin1", "test@test.com", "1111", "1111", false, "test.com", MemberRole.USER, MemberProvider.LOCAL);
        memberRepository.save(member);

        List<String> files = Arrays.asList("file1", "file2");
        List<String> hashTagList = Arrays.asList("좋은날", "생일");
        CreateRecordRequestDto requestDto = CreateRecordRequestDto.testInstance("thumbnailUrl", "title", "content", files, hashTagList, null);

        // when
        recordService.createRecord(member.getSeq(), requestDto);

        // then
        List<Record> recordList = recordRepository.findAll();
        assertThat(recordList.get(0).getContent()).isEqualTo(requestDto.getContent());
        assertThat(recordList.get(0).getTitle()).isEqualTo(requestDto.getTitle());
        List<File> fileList = fileRepository.findAll();
        assertThat(fileList).hasSize(2);
        List<RecordHashTag> recordHashTagList = recordHashTagRepository.findAll();
        assertThat(recordHashTagList).hasSize(2);
    }

}
