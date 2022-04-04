package com.hh.record.dto.record;

import com.hh.record.entity.record.Emotion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmotionRequestDTO {

    @NotBlank
    private Long recordSeq;

    @NotBlank
    private Emotion emotion;

}
