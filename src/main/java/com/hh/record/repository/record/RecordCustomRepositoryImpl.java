package com.hh.record.repository.record;

import com.hh.record.entity.Record;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static com.hh.record.entity.QFile.*;
import static com.hh.record.entity.QRecord.record;

@RequiredArgsConstructor
public class RecordCustomRepositoryImpl implements RecordCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Record> retrieveRecord(Long memberId, String code, String search, LocalDate date) {
        BooleanBuilder booleanBuilder = searchBuilder(code, search);
        return jpaQueryFactory.selectFrom(record)
//                .leftJoin(record.fileList, file).fetchJoin()
                .where(
                        record.member.seq.eq(memberId).and(booleanBuilder),
                        eqDate(date)
                )
                .orderBy(record.seq.desc())
                .fetch();
    }

    @Override
    public Optional<Record> findByMember_SeqAndSeq(Long memberId, Long recordId) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(record)
//                        .leftJoin(record.fileList, file).fetchJoin()
                        .where(
                                record.member.seq.eq(memberId),
                                record.seq.eq(recordId)
                        )
                        .fetchOne()
        );
    }

    @Override
    public List<LocalDateTime> findAllByDates(Long memberId, int year, int month) {
        LocalDateTime searchStartDate = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime searchEndDate = LocalDateTime.of(year, month + 1, 1, 0, 0);

        return jpaQueryFactory
                .select(record.regDate)
                .from(record)
                .where(record.regDate.between(searchStartDate, searchEndDate))
                .fetch();
    }

    private BooleanBuilder searchBuilder(String code, String search) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (code != null && search != null) {
            if (code.contains("t"))
                booleanBuilder.or(record.title.contains(search));
            if (code.contains("c"))
                booleanBuilder.or(record.content.contains(search));
        }
        return booleanBuilder;
    }

    private BooleanExpression eqDate(LocalDate date) {
        if (date == null) {
            return null;
        }
        LocalDateTime start = LocalDateTime.of(date, LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(date, LocalTime.MAX);
        return record.regDate.goe(start).and(record.regDate.loe(end));
    }

}
