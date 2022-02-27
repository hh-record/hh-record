package com.hh.record.service.record;

import com.hh.record.config.exception.errorCode.NotFoundException;
import com.hh.record.dto.record.CreateRecordRequestDto;
import com.hh.record.dto.record.RecordResponseDTO;
import com.hh.record.dto.record.RecordSearchRequestDTO;
import com.hh.record.dto.record.RecordUpdateRequestDTO;
import com.hh.record.entity.member.Member;
import com.hh.record.entity.Record;
import com.hh.record.repository.member.MemberRepository;
import com.hh.record.repository.record.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

    private final MemberRepository memberRepository;
    private final RecordRepository recordRepository;

    @Transactional(readOnly = true)
    @Override
    public List<RecordResponseDTO> retrieveRecord(Long memberId, RecordSearchRequestDTO requestDTO) {
        return recordRepository.retrieveRecord(memberId, requestDTO.getCode(), requestDTO.getSearch())
                .stream().map(RecordResponseDTO::of).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public RecordResponseDTO createRecord(Long memberId, CreateRecordRequestDto requestDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(String.format("존재하지 않는 멤버 %s 입니다.", memberId)));
        Record record = recordRepository.save(requestDto.dtoToEntity(member));
        record.addFile(requestDto.getFileKey());
        return RecordResponseDTO.of(record);
    }

    @Override
    public List<RecordResponseDTO> selectRecord(Long memberId) {
        return recordRepository.findAllByMember_Seq(memberId)
                .stream().map(RecordResponseDTO::of).collect(Collectors.toList());
    }

    @Override
    public RecordResponseDTO selectOneRecord(Long memberId, Long recordId) {
        Record record = recordRepository.findByMember_SeqAndSeq(memberId, recordId)
                .orElseThrow(() -> new NotFoundException("일기가 존재하지 않습니다."));
        return RecordResponseDTO.of(record);
    }

    @Transactional
    @Override
    public Long updateRecord(Long memberId, Long recordId, RecordUpdateRequestDTO requestDTO) {
        Record record = recordRepository.findByMember_SeqAndSeq(memberId, recordId)
                .orElseThrow(() -> new NotFoundException("일기가 존재하지 않습니다."));
        record.changeRecord(requestDTO.getTitle(), requestDTO.getContent(), requestDTO.getThumbnailUrl());
        return record.getSeq();
    }

    @Transactional
    @Override
    public Long deleteRecord(Long memberId, Long recordId) {
        recordRepository.deleteByMember_SeqAndSeq(memberId, recordId);
        return recordId;
    }

}
