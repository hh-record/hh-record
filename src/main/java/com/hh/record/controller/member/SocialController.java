package com.hh.record.controller.member;

import com.hh.record.config.interceptor.Auth;
import com.hh.record.config.interceptor.MemberId;
import com.hh.record.controller.ApiResponse;
import com.hh.record.dto.member.response.RecommendFriendsDTO;
import com.hh.record.service.member.MemberService;
import com.hh.record.service.social.SocialService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hh-record")
public class SocialController {

    private final SocialService socialService;

    @ApiOperation("추천 친구 불러오기")
    @Auth
    @GetMapping(value = "/recommendFriends", produces = "application/json")
    public ApiResponse<List<RecommendFriendsDTO>> recommendFriends(@MemberId Long memberId) {
        return ApiResponse.success(socialService.recommendFriends(memberId));
    }

}
