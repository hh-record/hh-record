package com.hh.record.dto.theme;

import com.hh.record.entity.Theme;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ThemeInfoResponse {

    private Long seq;

    private String content;

    public ThemeInfoResponse(Long seq, String content) {
        this.seq = seq;
        this.content = content;
    }

    public static ThemeInfoResponse of(Theme theme) {
        return new ThemeInfoResponse(theme.getSeq(), theme.getContent());
    }

}
