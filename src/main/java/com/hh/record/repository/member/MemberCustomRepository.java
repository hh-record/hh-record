package com.hh.record.repository.member;

import com.hh.record.entity.member.Member;

import java.util.Optional;

public interface MemberCustomRepository {

    String findByInfo(String userName, String phoneNumber, String email);

    Optional<Member> findByEmail(String email);

}
