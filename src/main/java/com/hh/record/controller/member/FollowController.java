package com.hh.record.controller.member;

import com.hh.record.config.interceptor.Auth;
import com.hh.record.config.interceptor.MemberId;
import com.hh.record.controller.ApiResponse;
import com.hh.record.dto.member.request.FollowMemberRequestDTO;
import com.hh.record.dto.member.response.MemberInfoResponse;
import com.hh.record.service.member.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("hh-record")
public class FollowController {

    private final MemberService memberService;

    @ApiOperation("친구 팔로우 하기")
    @Auth
    @PostMapping("/follow")
    public ApiResponse<String> followMember(@RequestBody @Valid FollowMemberRequestDTO requestDTO, @MemberId Long memberId) {
        memberService.followMember(requestDTO.getMemberSeq(), memberId);
        return ApiResponse.OK;
    }

    @ApiOperation("친구 언팔로우 하기")
    @Auth
    @PostMapping("/unfollow")
    public ApiResponse<String> unfollowMember(@RequestBody @Valid FollowMemberRequestDTO requestDTO, @MemberId Long memberId) {
        memberService.unfollowMember(requestDTO.getMemberSeq(), memberId);
        return ApiResponse.OK;
    }

    @ApiOperation("나의 팔로잉 리스트")
    @Auth
    @GetMapping("/following")
    public ApiResponse<List<MemberInfoResponse>> retrieveFollowingMember(@MemberId Long memberId) {
        return ApiResponse.success(memberService.retrieveFollowingMember(memberId));
    }

    @ApiOperation("나의 팔로워 리스트")
    @Auth
    @GetMapping("/follower")
    public ApiResponse<List<MemberInfoResponse>> retrieveFollowerMember(@MemberId Long memberId) {
        return ApiResponse.success(memberService.retrieveFollowerMember(memberId));
    }

    @ApiOperation("팔로우 한 사람 또는 무작위 멤버 정보 불러오기")
    @GetMapping("/follow/member/{followId}")
    public ApiResponse<MemberInfoResponse> getFollowMember(@PathVariable Long followId) {
        return ApiResponse.success(memberService.selectMemberDTO(followId));
    }

}
