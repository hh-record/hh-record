package com.hh.record.dto.record;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordSearchRequestDTO {

    @NotBlank(message = "검색어를 입력해주세요.")
    private String search;

    @NotBlank(message = "검색 조건을 선택해주세요.")
    private String code;

}
