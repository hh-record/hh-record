package com.hh.record.repository.record;

import com.hh.record.entity.Record;

import java.util.List;
import java.util.Optional;

public interface RecordCustomRepository {

    List<Record> retrieveRecord(Long memberId, String code, String search);

    Optional<Record> findByMember_SeqAndSeq(Long memberId, Long recordId);

}
