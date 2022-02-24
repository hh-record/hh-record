package com.hh.record.repository.record;

import com.hh.record.entity.Record;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import static com.hh.record.entity.QRecord.record;

@RequiredArgsConstructor
public class RecordCustomRepositoryImpl implements RecordCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Record> retrieveRecord(Long memberId, String code, String search) {
        BooleanBuilder booleanBuilder = searchBuilder(code, search);
        return jpaQueryFactory.selectFrom(record)
                .where(
                        record.member.seq.eq(memberId).and(booleanBuilder)
                )
                .orderBy(record.seq.desc())
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
}
