package com.hh.record.dto.member.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class FollowMemberRequestDTO {

    @NotNull
    private Long memberSeq;

}
