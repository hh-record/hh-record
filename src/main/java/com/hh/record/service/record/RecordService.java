package com.hh.record.service.record;

import com.hh.record.dto.record.*;
import com.hh.record.dto.theme.ThemeInfoResponse;

import java.time.LocalDate;
import java.util.List;

public interface RecordService {

    List<RecordResponseDTO> selectRecordList(Long memberId);

    List<RecordResponseDTO> retrieveRecord(Long memberId, RecordSearchRequestDTO requestDTO);

    RecordResponseDTO createRecord(Long memberId, CreateRecordRequestDto requestDto);

    List<LocalDate> selectRecordDate(Long memberId, RecordCalendarRequestDTO requestDTO);

    RecordResponseDTO selectOneRecord(Long memberId, Long recordId);

    Long updateRecord(Long memberId, Long recordId, RecordUpdateRequestDTO requestDTO);

    Long deleteRecord(Long memberId, Long recordId);

    RecordResponseDTO updateRecordFile(Long memberId, Long recordId, List<String> fileList);

    ThemeInfoResponse selectTheme();

}
