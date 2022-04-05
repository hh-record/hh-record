package com.hh.record.repository.member;

import com.hh.record.dto.member.response.RecommendFriendsDTO;
import com.hh.record.entity.member.Member;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.MimeTypeUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.hh.record.entity.member.QMember.member;
import static com.hh.record.entity.member.QMemberFollow.memberFollow;

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

    @Override
    public List<RecommendFriendsDTO> selectRecommendFriends(Long memberId) {
        List<Member> result = jpaQueryFactory
                .select(member)
                .from(member)
                .where(
                        member.isPrivate.eq(false)
                        .and(member.seq.ne(memberId))
                        .and(member.seq.notIn(checkFollow(memberId)))
                )
                .orderBy(Expressions.numberTemplate(Long.class, "function('rand')").asc())
                .limit(4L)
                .fetch();

        return result.stream().map(RecommendFriendsDTO::of).distinct().collect(Collectors.toList());
    }

    private List<Long> checkFollow(Long memberSeq) {
        return jpaQueryFactory
                .select(memberFollow.targetMember.seq)
                .from(memberFollow)
                .where(memberFollow.member.seq.eq(memberSeq))
                .fetch();
    }

}
