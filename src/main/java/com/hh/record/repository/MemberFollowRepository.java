package com.hh.record.repository;

import com.hh.record.entity.member.MemberFollow;
import com.hh.record.repository.member.MemberFollowCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberFollowRepository extends JpaRepository<MemberFollow, Long>, MemberFollowCustomRepository {
}
