package com.hh.record.dto.member.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter @Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMemberRequestDTO {

    @NotBlank(message = "회원 이름을 적어주세요.")
    private String userName;

    @NotBlank(message = "전화번호를 입력해주세요.")
    private String phoneNumber;

    private boolean isPrivate;

    private String profileImgUrl;

}
