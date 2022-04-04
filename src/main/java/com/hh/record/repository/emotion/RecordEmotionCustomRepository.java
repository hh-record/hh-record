package com.hh.record.repository.emotion;

import com.hh.record.dto.record.EmotionResponseDTO;
import com.hh.record.entity.record.RecordEmotion;

import java.util.List;
import java.util.Optional;

public interface RecordEmotionCustomRepository {

    Optional<RecordEmotion> findRecordEmotion(Long recordSeq, Long memberSeq);

    List<EmotionResponseDTO> findByRecordEmotion(Long recordId);
}
