package com.hh.record.repository.emotion;

import com.hh.record.entity.record.RecordEmotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordEmotionRepository extends JpaRepository<RecordEmotion, Long>, RecordEmotionCustomRepository{

}
