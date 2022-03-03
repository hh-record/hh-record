package com.hh.record.dto.record;

import com.hh.record.entity.member.Member;
import com.hh.record.entity.Record;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CreateRecordRequestDto {

    private String thumbnailUrl;

    private String title;

    private String content;

    private List<String> fileList = new ArrayList<>();

    public CreateRecordRequestDto(String thumbnailUrl, String title, String content, List<String> fileList) {
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.content = content;
        this.fileList = fileList;
    }

    public static CreateRecordRequestDto testInstance(String thumbnailUrl, String title, String content, List<String> fileList) {
        return new CreateRecordRequestDto(thumbnailUrl, title, content, fileList);
    }

    public Record dtoToEntity(Member member) {
        return new Record(member, thumbnailUrl, title, content);
    }

}
