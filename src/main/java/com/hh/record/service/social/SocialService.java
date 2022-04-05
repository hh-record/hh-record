package com.hh.record.service.social;

import com.hh.record.dto.member.response.RecommendFriendsDTO;

import java.util.List;

public interface SocialService {

    List<RecommendFriendsDTO> recommendFriends(Long memberId);

}
