package com.hh.record.dto.member.response;

import com.hh.record.entity.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberInfoResponse {

    private Long seq;

    private String id;

    private String userName;

    private String email;

    private String phoneNumber;

    private boolean isPrivate;

    private String profileImgUrl;

    public MemberInfoResponse(Long seq, String id, String userName, String email, String phoneNumber, boolean isPrivate, String profileImgUrl) {
        this.seq = seq;
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isPrivate = isPrivate;
        this.profileImgUrl = profileImgUrl;
    }

    public static MemberInfoResponse of(Member member) {
        return new MemberInfoResponse(member.getSeq(), member.getId(), member.getUserName(), member.getEmail(), member.getPhoneNumber(), member.getIsPrivate(), member.getProfileImgUrl());
    }

}
