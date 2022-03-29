package com.hh.record.controller.record;

import com.hh.record.config.interceptor.Auth;
import com.hh.record.config.interceptor.MemberId;
import com.hh.record.controller.ApiResponse;
import com.hh.record.dto.record.RecordResponseDTO;
import com.hh.record.service.record.RecordFollowService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
