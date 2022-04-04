package com.hh.record.service.record;

import com.hh.record.dto.record.EmotionRequestDTO;

public interface RecordEmotionService {
    void insertEmotion(Long memberId, EmotionRequestDTO requestDTO);

    void deleteEmotion(Long memberId, EmotionRequestDTO requestDTO);
}
