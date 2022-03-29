package com.hh.record.dto.member.response;

import com.hh.record.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberInfoResponse {

    private Long seq;

    private String id;

    private String userName;

    private String email;

    private String phoneNumber;

    private Boolean isPrivate;

    private String profileImgUrl;

    private long followerCount;

    private long followingCount;

    public static MemberInfoResponse of(Member member) {
        return MemberInfoResponse.builder()
                .seq(member.getSeq())
                .id(member.getId())
                .userName(member.getUserName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .isPrivate(member.getIsPrivate())
                .profileImgUrl(member.getProfileImgUrl())
                .followingCount(member.getMemberList().size())
                .followerCount(member.getTargetMemberList().size())
                .build();
    }

}
