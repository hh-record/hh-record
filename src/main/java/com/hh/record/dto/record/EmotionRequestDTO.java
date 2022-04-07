package com.hh.record.dto.record;

import com.hh.record.entity.record.Emotion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmotionRequestDTO {

    @NotNull
    private Long recordSeq;

    @NotNull
    private Emotion emotion;

}
