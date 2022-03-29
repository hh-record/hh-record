package com.hh.record.service.record;

import com.hh.record.dto.record.RecordResponseDTO;

import java.util.List;

public interface RecordFollowService {

    RecordResponseDTO selectOneRecord(Long memberId, Long recordId);

    List<RecordResponseDTO> retrieveRecord(Long memberId, Long followId);

}
