package com.hh.record.dto.record;

import com.hh.record.entity.Record;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String fileUrl;

    public static RecordResponseDTO of(Record record) {
        String baseUrl = "https://hh-record-project.s3.ap-northeast-2.amazonaws.com/";

        return RecordResponseDTO.builder()
                .record_seq(record.getSeq())
                .title(record.getTitle())
                .content(record.getContent())
                .regDate(record.getRegDate().toString())
                .modDate(record.getModDate().toString())
                .fileUrl(record.getFile() == null ? null : baseUrl + record.getFile().getFileKey())
                .build();
    }

}
