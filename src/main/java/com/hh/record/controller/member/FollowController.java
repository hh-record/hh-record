package com.hh.record.controller.member;

import com.hh.record.config.interceptor.Auth;
import com.hh.record.config.interceptor.MemberId;
import com.hh.record.controller.ApiResponse;
import com.hh.record.dto.member.request.FollowMemberRequestDTO;
import com.hh.record.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("hh-record")
public class FollowController {

    private final MemberService memberService;

    @Auth
    @PostMapping("/follow")
    public ApiResponse<String> followMember(@RequestBody @Valid FollowMemberRequestDTO requestDTO, @MemberId Long memberId) {
        memberService.followMember(requestDTO.getMemberSeq(), memberId);
        return ApiResponse.OK;
    }

}
