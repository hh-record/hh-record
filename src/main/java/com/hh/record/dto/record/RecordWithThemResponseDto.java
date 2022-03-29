package com.hh.record.dto.record;

import com.hh.record.dto.theme.ThemeInfoResponse;
import com.hh.record.entity.Theme;
import com.hh.record.entity.record.Record;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecordWithThemResponseDto {

    private RecordResponseDTO record;

    private ThemeInfoResponse theme;

    public static RecordWithThemResponseDto of(Record record, Theme theme) {
        return new RecordWithThemResponseDto(RecordResponseDTO.of(record), theme == null ? null : ThemeInfoResponse.of(theme));
    }

}
