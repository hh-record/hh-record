package com.hh.record.dto.certification;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CertificationRequestDTO {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String id;

    @NotBlank(message = "메일을 입력해주세요.")
    private String email;


}
