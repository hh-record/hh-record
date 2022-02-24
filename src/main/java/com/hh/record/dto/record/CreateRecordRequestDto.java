package com.hh.record.dto.record;

import com.hh.record.entity.Member;
import com.hh.record.entity.Record;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateRecordRequestDto {

    private String thumbnailUrl;

    private String title;

    private String content;

    private String fileKey;

    public CreateRecordRequestDto(String thumbnailUrl, String title, String content, String fileKey) {
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.content = content;
        this.fileKey = fileKey;
    }

    public static CreateRecordRequestDto testInstance(String thumbnailUrl, String title, String content, String fileKey) {
        return new CreateRecordRequestDto(thumbnailUrl, title, content, fileKey);
    }

    public Record dtoToEntity(Member member) {
        return new Record(member, thumbnailUrl, title, content);
    }

}
