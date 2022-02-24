package com.hh.record.repository.record;

import com.hh.record.entity.Record;

import java.util.List;

public interface RecordCustomRepository {

    List<Record> retrieveRecord(Long memberId, String code, String search);

}
