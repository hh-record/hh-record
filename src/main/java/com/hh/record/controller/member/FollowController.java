package com.hh.record.controller.member;

import com.hh.record.config.interceptor.Auth;
import com.hh.record.config.interceptor.MemberId;
import com.hh.record.controller.ApiResponse;
import com.hh.record.dto.member.request.FollowMemberRequestDTO;
import com.hh.record.dto.member.response.MemberInfoResponse;
import com.hh.record.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @Auth
    @PostMapping("/unfollow")
    public ApiResponse<String> unfollowMember(@RequestBody @Valid FollowMemberRequestDTO requestDTO, @MemberId Long memberId) {
        memberService.unfollowMember(requestDTO.getMemberSeq(), memberId);
        return ApiResponse.OK;
    }

    @Auth
    @GetMapping("/following")
    public ApiResponse<List<MemberInfoResponse>> retrieveFollowingMember(@MemberId Long memberId) {
        return ApiResponse.success(memberService.retrieveFollowingMember(memberId));
    }

    @Auth
    @GetMapping("/follower")
    public ApiResponse<List<MemberInfoResponse>> retrieveFollowerMember(@MemberId Long memberId) {
        return ApiResponse.success(memberService.retrieveFollowerMember(memberId));
    }

}
