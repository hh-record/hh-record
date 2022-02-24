package com.hh.record.dto.member.response;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter @Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SelectMemberResponseDTO {

    private Long seq;

    @NotBlank(message = "회원 아이디를 넣어주세요.")
    private String id;

    @NotBlank(message = "회원 이름을 적어주세요.")
    private String userName;

    @Email(message = "알맞지 않은 이메일 형식입니다.")
    private String email;

    @NotBlank(message = "전화번호를 입력해주세요.")
    private String phoneNumber;

}
