package com.hh.record.dto.record;

import com.hh.record.entity.member.Member;
import com.hh.record.entity.Record;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CreateRecordRequestDto {

    private String thumbnailUrl;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private List<String> fileList = new ArrayList<>();

    private List<String> hashTagList = new ArrayList<>();

    private final Boolean isPrivate = Boolean.TRUE;

    private String themeUse;

    public CreateRecordRequestDto(String thumbnailUrl, String title, String content, List<String> fileList, List<String> hashTagList, String themeUse) {
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.content = content;
        this.fileList = fileList;
        this.hashTagList = hashTagList;
        this.themeUse = themeUse;
    }

    public static CreateRecordRequestDto testInstance(String thumbnailUrl, String title, String content, List<String> fileList, List<String> hashTagList, String themeUse) {
        return new CreateRecordRequestDto(thumbnailUrl, title, content, fileList, hashTagList, themeUse);
    }

    public Record dtoToEntity(Member member) {
        return new Record(member, thumbnailUrl, title, content, isPrivate, themeUse);
    }

}
