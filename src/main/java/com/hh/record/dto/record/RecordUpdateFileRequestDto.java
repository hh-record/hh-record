package com.hh.record.dto.record;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RecordUpdateFileRequestDto {

    private List<String> fileList;

}
