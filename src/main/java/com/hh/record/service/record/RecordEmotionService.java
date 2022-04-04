package com.hh.record.service.record;

import com.hh.record.dto.record.EmotionRequestDTO;
import com.hh.record.dto.record.EmotionResponseDTO;

import java.util.List;

public interface RecordEmotionService {
    void insertEmotion(EmotionRequestDTO requestDTO);

    void deleteEmotion(Long memberId, EmotionRequestDTO requestDTO);

    List<EmotionResponseDTO> findByRecordEmotion(Long memberId, Long recordId);
}
