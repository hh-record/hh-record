package com.hh.record.controller.member;

import com.hh.record.controller.ApiResponse;
import com.hh.record.dto.certification.CertificationCheckRequestDTO;
import com.hh.record.dto.member.request.LoginMemberRequestDTO;
import com.hh.record.dto.member.request.SelectMemberIdRequestDTO;
import com.hh.record.dto.member.request.InsertMemberRequestDTO;
import com.hh.record.dto.certification.CertificationRequestDTO;
import com.hh.record.service.mail.MailService;
import com.hh.record.service.member.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hh-record-intro")
public class LoginController {

    private final MemberService memberService;

    private final MailService mailService;

    @ApiOperation("회원 로그인")
    @PostMapping(value = "/login", produces = "application/json")
    public ApiResponse<Object> loginMember(@RequestBody LoginMemberRequestDTO requestDTO) {
        String token = memberService.loginMember(requestDTO.getId(), requestDTO.getPassword());
        return ApiResponse.success(token);
    }

    @ApiOperation("회원가입")
    @PostMapping("/sign-up")
    public ApiResponse<String> insertMember(@RequestBody @Valid InsertMemberRequestDTO request) {
        memberService.insertMember(request);
        return ApiResponse.OK;
    }

    @ApiOperation("아이디 찾기")
    @PostMapping("/id")
    public ApiResponse<String> selectMemberId(@RequestBody SelectMemberIdRequestDTO requestDTO) {
        String memberId = memberService.selectMemberId(requestDTO);
        return memberId == null ? ApiResponse.unauthorized("회원정보가 존재하지 않습니다.") : ApiResponse.success(memberId);
    }

    @ApiOperation("회원 이메일 인증")
    @PostMapping("/user-certification")
    public ApiResponse<String> memberCertification(@RequestBody CertificationRequestDTO requestDTO) {
        memberService.certificationMember(requestDTO);
        mailService.sendMail(requestDTO.getEmail());
        return ApiResponse.OK;
    }

    @ApiOperation("회원 이메일 인증 확인")
    @PostMapping("/user-certification-check")
    public ApiResponse<String> memberCheckCertification(@RequestBody CertificationCheckRequestDTO requestDTO) {
        mailService.checkCertification(requestDTO.getCertificationNumber());
        return ApiResponse.OK;
    }

}
