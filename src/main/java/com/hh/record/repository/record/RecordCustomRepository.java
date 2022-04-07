package com.hh.record.repository.record;

import com.hh.record.dto.record.RecordResponseDTO;
import com.hh.record.entity.record.IsPrivate;
import com.hh.record.entity.record.Record;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RecordCustomRepository {

    List<Record> retrieveRecord(Long memberId, String code, String search, LocalDate date, List<IsPrivate> isPrivateList);

    Optional<Record> findByMember_SeqAndSeq(Long memberId, Long recordId);

    List<LocalDateTime> findAllByDates(Long memberId, int year, int month);

    Optional<Record> selectOneRecord(Long memberId, Long recordId);

}
