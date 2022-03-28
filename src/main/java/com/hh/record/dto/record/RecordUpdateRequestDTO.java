package com.hh.record.dto.record;

import com.hh.record.entity.record.IsPrivate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordUpdateRequestDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private String thumbnailUrl;

    @NotBlank
    private String themeUse;

    private IsPrivate isPrivate;

}
