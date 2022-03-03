package com.hh.record.service.record;

import com.hh.record.dto.record.CreateRecordRequestDto;
import com.hh.record.dto.record.RecordResponseDTO;
import com.hh.record.dto.record.RecordSearchRequestDTO;
import com.hh.record.dto.record.RecordUpdateRequestDTO;

import java.util.List;

public interface RecordService {

    List<RecordResponseDTO> retrieveRecord(Long memberId, RecordSearchRequestDTO requestDTO);

    RecordResponseDTO createRecord(Long memberId, CreateRecordRequestDto requestDto);

    List<RecordResponseDTO> selectRecord(Long memberId);

    RecordResponseDTO selectOneRecord(Long memberId, Long recordId);

    Long updateRecord(Long memberId, Long recordId, RecordUpdateRequestDTO requestDTO);

    Long deleteRecord(Long memberId, Long recordId);

    RecordResponseDTO updateRecordFile(Long memberId, Long recordId, List<String> fileList);

}
