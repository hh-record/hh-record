package com.hh.record.dto.record;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordUpdateRequestDTO {

    private String title;

    private String content;

    private String thumbnailUrl;

    private String themeUse;

}
