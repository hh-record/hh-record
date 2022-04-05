package com.hh.record.dto.member.response;

import com.hh.record.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendFriendsDTO {

    private Long seq;

    private String id;

    private String profileImgUrl;

    public static RecommendFriendsDTO of(Member member) {
        return new RecommendFriendsDTO(member.getSeq(), member.getId(), member.getProfileImgUrl());
    }

}
