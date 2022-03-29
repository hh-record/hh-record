package com.hh.record.repository.member;

public interface MemberFollowCustomRepository {

    boolean validateFollow(Long memberId, Long targetMemberId);

}
