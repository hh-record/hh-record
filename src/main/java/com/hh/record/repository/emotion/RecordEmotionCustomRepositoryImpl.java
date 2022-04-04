package com.hh.record.repository.emotion;

import com.hh.record.dto.record.EmotionResponseDTO;
import com.hh.record.entity.record.RecordEmotion;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.hh.record.entity.record.QRecordEmotion.recordEmotion;

@RequiredArgsConstructor
public class RecordEmotionCustomRepositoryImpl implements RecordEmotionCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<RecordEmotion> findRecordEmotion(Long recordSeq, Long memberSeq) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(recordEmotion)
                    .where(
                            recordEmotion.record.seq.eq(recordSeq)
                            .and(recordEmotion.member.seq.eq(memberSeq))
                    )
                    .fetchOne()
                );
    }

    @Override
    public List<EmotionResponseDTO> findByRecordEmotion(Long recordId) {
        List<RecordEmotion> result = jpaQueryFactory.selectFrom(recordEmotion)
                .where(recordEmotion.record.seq.eq(recordId))
                .fetch();
        return result.stream().map(EmotionResponseDTO::of).distinct().collect(Collectors.toList());
    }
}
