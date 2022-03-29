package com.hh.record.service.record;

import com.hh.record.dto.record.RecordWithThemResponseDto;

public interface RecordFollowService {

    RecordWithThemResponseDto selectOneRecord(Long memberId, Long recordId);

}
