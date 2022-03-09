package com.hh.record.dto.record;

import com.hh.record.entity.File;
import com.hh.record.entity.Record;
import com.hh.record.entity.RecordHashTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordResponseDTO {

    private Long record_seq;

    private String title;

    private String content;

    private String regDate;

    private String modDate;

    private List<String> fileUrl;

    private List<String> hashTagList;

    private String thumbnailUrl;

    public static RecordResponseDTO of(Record record) {
        return RecordResponseDTO.builder()
                .record_seq(record.getSeq())
                .thumbnailUrl(record.getThumbnailUrl())
                .title(record.getTitle())
                .content(record.getContent())
                .regDate(record.getRegDate().toString())
                .modDate(record.getModDate().toString())
                .fileUrl(record.getFileList().stream().map(File::getFileKey).collect(Collectors.toList()))
                .hashTagList(record.getRecordHashTagList().stream().map(RecordHashTag::getHashTag).collect(Collectors.toList()))
                .build();
    }

}
