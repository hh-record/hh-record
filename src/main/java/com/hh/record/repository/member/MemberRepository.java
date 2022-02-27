package com.hh.record.repository.member;

import com.hh.record.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberCustomRepository {

    Optional<Member> findById(String id);

    Optional<Member> findByIdAndEmail(String id, String email);

}
