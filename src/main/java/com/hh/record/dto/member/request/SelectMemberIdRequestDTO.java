package com.hh.record.dto.member.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SelectMemberIdRequestDTO {

    @NotBlank(message = "회원 이름을 넣어주세요.")
    private String userName;

    @NotBlank(message = "회원 전화번호를 넣어주세요.")
    private String phoneNumber;

    @NotBlank(message = "회원 이메일을 넣어주세요.")
    private String email;

}
