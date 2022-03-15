package com.hh.record.service.member;

import com.hh.record.dto.certification.CertificationRequestDTO;
import com.hh.record.dto.member.request.SelectMemberIdRequestDTO;
import com.hh.record.dto.member.request.UpdateMemberRequestDTO;
import com.hh.record.dto.member.response.MemberInfoResponse;
import com.hh.record.dto.member.request.InsertMemberRequestDTO;

public interface MemberService {

    MemberInfoResponse selectMemberDTO(Long memberId);

    void updateMember(Long id, UpdateMemberRequestDTO updateMemberRequestDTO);

    void deleteMember(Long id);

    void insertMember(InsertMemberRequestDTO request);

    void checkMemberPassword(Long id, String password);

    void updateMemberPassword(Long id, String password);

    String selectMemberId(SelectMemberIdRequestDTO requestDTO);

    void certificationMember(CertificationRequestDTO requestDTO);

    String loginMember(String id, String password);

    void followMember(Long followMemberSeq, Long memberId);

    void unfollowMember(Long memberSeq, Long memberId);

}
