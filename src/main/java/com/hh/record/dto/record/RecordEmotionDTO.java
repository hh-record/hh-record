package com.hh.record.dto.record;

import com.hh.record.entity.record.Emotion;
import com.hh.record.entity.record.RecordEmotion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecordEmotionDTO {

    private Long likeCounts;
    private Long sadCounts;
    private Long cheerUpCounts;

    public static RecordEmotionDTO of(List<RecordEmotion> recordEmotionList) {
        long likeCounts = recordEmotionList.stream().filter(recordEmotion -> recordEmotion.getEmotion().equals(Emotion.LIKE)).count();
        long sadCounts = recordEmotionList.stream().filter(recordEmotion -> recordEmotion.getEmotion().equals(Emotion.SAD)).count();
        long cheerUpCounts = recordEmotionList.stream().filter(recordEmotion -> recordEmotion.getEmotion().equals(Emotion.CHEER_UP)).count();
        return new RecordEmotionDTO(likeCounts, sadCounts, cheerUpCounts);
    }

}
