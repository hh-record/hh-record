package com.hh.record.dto.record;

import com.hh.record.entity.record.Emotion;
import com.hh.record.entity.record.RecordEmotion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmotionResponseDTO {

    private Long emotionSeq;

    private Long recordSeq;

    private Long memberSeq;

    private Long writeMemberSeq;

    private Emotion emotion;

    public static EmotionResponseDTO of(RecordEmotion emotion) {
        return EmotionResponseDTO.builder()
                .emotionSeq(emotion.getSeq())
                .recordSeq(emotion.getRecord().getSeq())
                .memberSeq(emotion.getMember().getSeq())
                .writeMemberSeq(emotion.getRecord().getMember().getSeq())
                .emotion(emotion.getEmotion())
                .build();
    }

}
