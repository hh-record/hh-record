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

    private String thumbnailUrl;

    public static RecordResponseDTO of(Record record) {
        return RecordResponseDTO.builder()
                .record_seq(record.getSeq())
                .thumbnailUrl(record.getThumbnailUrl())
                .title(record.getTitle())
                .content(record.getContent())
                .regDate(record.getRegDate().toString())
                .modDate(record.getModDate().toString())
                .fileUrl(record.getFile() == null ? null : record.getFile().getFileKey())
                .build();
    }

}
