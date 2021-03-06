package com.hh.record.controller.member;

import com.hh.record.config.interceptor.Auth;
import com.hh.record.config.interceptor.MemberId;
import com.hh.record.controller.ApiResponse;
import com.hh.record.dto.member.MemberIsPrivateDTO;
import com.hh.record.dto.member.MemberPasswordDTO;
import com.hh.record.dto.member.request.UpdateMemberRequestDTO;
import com.hh.record.dto.member.response.MemberInfoResponse;
import com.hh.record.service.member.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hh-record/my-page")
public class MyPageController {

    private final MemberService memberService;

    @ApiOperation("내 정보 불러오기")
    @Auth
    @GetMapping(value = "/", produces = "application/json")
    public ApiResponse<MemberInfoResponse> selectOneMember(@MemberId Long memberId) {
        return ApiResponse.success(memberService.selectMemberDTO(memberId));
    }

    @ApiOperation("내 정보 수정하기")
    @Auth
    @PutMapping(value = "/", produces = "application/json")
    public ApiResponse<String> updateMember(@MemberId Long memberId, @Valid @RequestBody UpdateMemberRequestDTO requestDTO) {
        memberService.updateMember(memberId, requestDTO);
        return ApiResponse.OK;
    }

    @ApiOperation("회원 비밀번호 확인")
    @Auth
    @PostMapping(value = "/password-status", produces = "application/json")
    public ApiResponse<String> checkMemberPassword(@MemberId Long memberId, @RequestBody MemberPasswordDTO passwordDTO) {
        memberService.checkMemberPassword(memberId, passwordDTO.getPassword());
        return ApiResponse.OK;
    }

    @ApiOperation("회원 비밀번호 변경")
    @Auth
    @PostMapping(value = "/password", produces = "application/json")
    public ApiResponse<String> updateMemberPassword(@MemberId Long memberId, @RequestBody MemberPasswordDTO passwordDTO) {
        memberService.updateMemberPassword(memberId, passwordDTO.getPassword());
        return ApiResponse.OK;
    }

    @ApiOperation("회원 탈퇴하기")
    @Auth
    @DeleteMapping(value = "/", produces = "application/json")
    public ApiResponse<String> deleteMember(@MemberId Long memberId) {
        memberService.deleteMember(memberId);
        return ApiResponse.OK;
    }

    @ApiOperation("프로필 공개 / 비공개 설정")
    @Auth
    @DeleteMapping(value = "/isPrivate", produces = "application/json")
    public ApiResponse<String> updateIsPrivateMember(@MemberId Long memberId, @RequestBody MemberIsPrivateDTO privateDTO) {
        memberService.updateIsPrivateMember(memberId, privateDTO.isPrivate());
        return ApiResponse.OK;
    }

}
