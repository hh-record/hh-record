package com.hh.record.service.record;

import com.hh.record.config.exception.errorCode.NotFoundException;
import com.hh.record.config.exception.errorCode.ValidationException;
import com.hh.record.dto.record.EmotionRequestDTO;
import com.hh.record.dto.record.EmotionResponseDTO;
import com.hh.record.entity.member.Member;
import com.hh.record.entity.record.Record;
import com.hh.record.entity.record.RecordEmotion;
import com.hh.record.repository.member.MemberRepository;
import com.hh.record.repository.emotion.RecordEmotionRepository;
import com.hh.record.repository.record.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RecordEmotionServiceImpl implements RecordEmotionService {

    private final RecordEmotionRepository emotionRepository;
    private final MemberRepository memberRepository;
    private final RecordRepository recordRepository;

    @Override
    public void insertEmotion(EmotionRequestDTO requestDTO) {
        RecordEmotion emotion = validationCheckRequest(requestDTO);
        emotionRepository.save(emotion);
    }

    @Transactional
    @Override
    public void deleteEmotion(Long memberId, EmotionRequestDTO requestDTO) {
        RecordEmotion emotion = emotionRepository.findRecordEmotion(requestDTO.getRecordSeq(), memberId)
                .orElseThrow(() -> new NotFoundException("존재하지 않은 감정입니다."));
        emotionRepository.delete(emotion);
    }

    @Override
    public List<EmotionResponseDTO> findByRecordEmotion(Long memberId, Long recordId) {
        return emotionRepository.findByRecordEmotion(recordId);
    }

    private RecordEmotion validationCheckRequest(EmotionRequestDTO requestDTO) {
        Record record = recordRepository.findById(requestDTO.getRecordSeq())
                .orElseThrow(() -> new NotFoundException("존재하지 않은 일기입니다."));
        Member member = memberRepository.findById(record.getMember().getId())
                .orElseThrow(() -> new NotFoundException("존재하지 않은 멤버입니다."));
        emotionRepository.findRecordEmotion(record.getSeq(), member.getSeq())
                .ifPresent(emotion ->  { throw new ValidationException("이미 해당 일기에 감정이 등록 되어있습니다."); });
        return new RecordEmotion(record, member, requestDTO.getEmotion());
    }
}
