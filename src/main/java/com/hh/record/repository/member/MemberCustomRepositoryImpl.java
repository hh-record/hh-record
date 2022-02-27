package com.hh.record.repository.member;

import com.hh.record.entity.member.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.hh.record.entity.member.QMember.member;

@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public String findByInfo(String userName, String phoneNumber, String email) {
        Member selectMember = jpaQueryFactory.selectFrom(member)
                .where(
                        member.userName.eq(userName)
                       .and(member.phoneNumber.eq(phoneNumber))
                       .and(member.email.eq(email))
                ).fetchOne();
        return selectMember != null ? selectMember.getId() : null;
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(member)
                        .where(
                                member.email.eq(email)
                        )
                        .fetchOne()
        );
    }

}
