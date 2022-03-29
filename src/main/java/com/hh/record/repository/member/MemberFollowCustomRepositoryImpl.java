package com.hh.record.repository.member;

import com.hh.record.entity.member.MemberFollow;
import com.hh.record.entity.member.QMemberFollow;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberFollowCustomRepositoryImpl implements MemberFollowCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean validateFollow(Long memberId, Long targetMemberId) {
        MemberFollow memberFollow = queryFactory.selectFrom(QMemberFollow.memberFollow)
                .where(
                        QMemberFollow.memberFollow.member.seq.eq(memberId),
                        QMemberFollow.memberFollow.targetMember.seq.eq(targetMemberId)
                )
                .fetchFirst();
        return memberFollow != null;
    }

}
