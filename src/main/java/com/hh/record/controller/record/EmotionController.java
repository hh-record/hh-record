package com.hh.record.controller.record;

import com.hh.record.config.interceptor.Auth;
import com.hh.record.config.interceptor.MemberId;
import com.hh.record.controller.ApiResponse;
import com.hh.record.dto.record.EmotionRequestDTO;
import com.hh.record.service.record.RecordEmotionService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hh-record")
@RequiredArgsConstructor
public class EmotionController {

    private final RecordEmotionService emotionService;

    @ApiOperation("감정 추가")
    @Auth
    @PostMapping("/emotion")
    public ApiResponse<String> insertEmotion(@MemberId Long memberId, @RequestBody EmotionRequestDTO requestDTO) {
        emotionService.insertEmotion(memberId, requestDTO);
        return ApiResponse.OK;
    }

    @ApiOperation("감정 삭제")
    @Auth
    @DeleteMapping ("/emotion")
    public ApiResponse<String> deleteEmotion(@MemberId Long memberId, @RequestBody EmotionRequestDTO requestDTO) {
        emotionService.deleteEmotion(memberId, requestDTO);
        return ApiResponse.OK;
    }

}
