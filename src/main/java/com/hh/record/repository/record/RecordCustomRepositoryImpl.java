package com.hh.record.repository.record;

import com.hh.record.entity.record.IsPrivate;
import com.hh.record.entity.record.Record;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static com.hh.record.entity.QFile.file;
import static com.hh.record.entity.record.QRecord.record;
import static com.hh.record.entity.record.QRecordHashTag.recordHashTag;

@RequiredArgsConstructor
public class RecordCustomRepositoryImpl implements RecordCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    /**
     * OneToMany에서는 다중 FetchJoin 불가.
     * default_batch_fetch_size: 1000 -> n+1 문제 해결
     */
    @Override
    public List<Record> retrieveRecord(Long memberId, String code, String search, LocalDate date, List<IsPrivate> isPrivateList) {
        BooleanBuilder booleanBuilder = searchBuilder(code, search);
        return jpaQueryFactory.selectFrom(record).distinct()
                .leftJoin(record.fileList, file)
                .leftJoin(record.recordHashTagList, recordHashTag)
                .where(
                        record.member.seq.eq(memberId).and(booleanBuilder),
                        eqDate(date),
                        eqIsPrivateList(isPrivateList)
                )
                .orderBy(record.seq.desc())
                .fetch();
    }

    @Override
    public Optional<Record> findByMember_SeqAndSeq(Long memberId, Long recordId) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(record)
                        .leftJoin(record.fileList, file).fetchJoin()
                        .where(
                                eqMemberId(memberId),
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

    @Override
    public Optional<Record> selectOneRecord(Long memberId, Long recordId) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(record)
                        .leftJoin(record.fileList, file).fetchJoin()
                        .where(
                                record.member.seq.eq(memberId),
                                record.seq.eq(recordId)
                        )
                        .fetchOne()
        );
    }

    private BooleanBuilder searchBuilder(String code, String search) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (code != null && search != null) {
            if (code.contains("t"))
                booleanBuilder.or(record.title.contains(search));
            if (code.contains("c"))
                booleanBuilder.or(record.content.contains(search));
            if (code.contains("g"))
                booleanBuilder.or(recordHashTag.hashTag.contains(search));
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

    private BooleanExpression eqMemberId(Long memberId) {
        if (memberId == null) {
            return null;
        }
        return record.member.seq.eq(memberId);
    }

    private Predicate eqIsPrivateList(List<IsPrivate> isPrivateList) {
        if (ObjectUtils.isEmpty(isPrivateList)) {
            return null;
        }
        return record.isPrivate.in(isPrivateList);
    }

}
