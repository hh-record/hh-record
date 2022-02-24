package com.hh.record.dto.certification;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CertificationCheckRequestDTO {

    @NotBlank(message = "인증번호를 입력해주세요.")
    private String certificationNumber;

}
