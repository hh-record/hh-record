package com.hh.record.service.record;

import com.hh.record.dto.record.EmotionRequestDTO;
import com.hh.record.dto.record.EmotionResponseDTO;

import java.util.List;

public interface RecordEmotionService {
    void insertEmotion(Long memberId, EmotionRequestDTO requestDTO);

    void deleteEmotion(Long memberId, Long recordId);

    List<EmotionResponseDTO> findByRecordEmotion(Long recordId);
}
