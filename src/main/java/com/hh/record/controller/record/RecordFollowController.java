package com.hh.record.controller.record;

import com.hh.record.config.interceptor.Auth;
import com.hh.record.config.interceptor.MemberId;
import com.hh.record.controller.ApiResponse;
import com.hh.record.dto.record.RecordResponseDTO;
import com.hh.record.dto.record.RecordSearchRequestDTO;
import com.hh.record.service.record.RecordFollowService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hh-record")
public class RecordFollowController {

    private final RecordFollowService recordFollowService;

    @ApiOperation("친구의 일기 상세 조회")
    @Auth
    @PostMapping("/records/follow/{recordId}")
    public ApiResponse<RecordResponseDTO> selectOneRecord(@MemberId Long memberId, @PathVariable("recordId") Long recordId) {
        return ApiResponse.success(recordFollowService.selectOneRecord(memberId, recordId));
    }

    @ApiOperation("친구의 일기 리스트 조회 followId -> 팔로우 한 친구의 아이디")
    @Auth
    @PostMapping("/records/follow/list/{followId}")
    public ApiResponse<List<RecordResponseDTO>> retrieveRecord(@MemberId Long memberId, @PathVariable Long followId, @RequestBody RecordSearchRequestDTO requestDTO) {
        return ApiResponse.success(recordFollowService.retrieveRecord(memberId, followId, requestDTO));
    }

}
