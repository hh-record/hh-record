package com.hh.record.repository.record;

import com.hh.record.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long>, RecordCustomRepository {

    List<Record> findAllByMember_Seq(Long seq);

    void deleteByMember_SeqAndSeq(Long memberId, Long seq);

}
