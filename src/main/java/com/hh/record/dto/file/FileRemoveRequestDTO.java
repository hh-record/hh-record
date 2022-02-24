package com.hh.record.dto.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileRemoveRequestDTO {

    @NotBlank(message = "파일 URL를 입력해주세요.")
    private String fileUrl;

}
