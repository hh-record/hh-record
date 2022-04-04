package com.hh.record.repository.emotion;

import com.hh.record.entity.record.RecordEmotion;

import java.util.Optional;

public interface RecordEmotionCustomRepository {

    Optional<RecordEmotion> findRecordEmotion(Long recordSeq, Long writeMemberSeq, Long memberSeq);

}
