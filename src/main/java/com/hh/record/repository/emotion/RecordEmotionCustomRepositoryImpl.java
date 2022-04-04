package com.hh.record.repository.emotion;

import com.hh.record.entity.member.Member;
import com.hh.record.entity.record.QRecordEmotion;
import com.hh.record.entity.record.Record;
import com.hh.record.entity.record.RecordEmotion;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.hh.record.entity.record.QRecordEmotion.recordEmotion;

@RequiredArgsConstructor
public class RecordEmotionCustomRepositoryImpl implements RecordEmotionCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<RecordEmotion> findRecordEmotion(Long recordSeq, Long writeMemberSeq, Long memberSeq) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(recordEmotion)
                    .where(
                            recordEmotion.record.seq.eq(recordSeq)
                            .and(recordEmotion.writeMember.seq.eq(writeMemberSeq))
                            .and(recordEmotion.member.seq.eq(memberSeq))
                    )
                    .fetchOne()
        );
    }
}
