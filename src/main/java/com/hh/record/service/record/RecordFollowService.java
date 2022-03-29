package com.hh.record.service.record;

import com.hh.record.dto.record.RecordResponseDTO;

public interface RecordFollowService {

    RecordResponseDTO selectOneRecord(Long memberId, Long recordId);

}
