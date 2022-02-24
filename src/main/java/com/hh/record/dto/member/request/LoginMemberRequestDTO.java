package com.hh.record.dto.member.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginMemberRequestDTO {

    @NotBlank(message = "회원 아이디를 넣어주세요.")
    private String id;

    @NotBlank(message = "회원 비밀번호를 넣어주세요.")
    private String password;

}
