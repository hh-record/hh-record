package com.hh.record.controller.record;

import com.hh.record.config.interceptor.Auth;
import com.hh.record.config.interceptor.MemberId;
import com.hh.record.controller.ApiResponse;
import com.hh.record.dto.record.EmotionRequestDTO;
import com.hh.record.dto.record.EmotionResponseDTO;
import com.hh.record.service.record.RecordEmotionService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/hh-record")
@RequiredArgsConstructor
public class EmotionController {

    private final RecordEmotionService emotionService;

    @ApiOperation("감정 추가")
    @Auth
    @PostMapping("/emotion")
    public ApiResponse<String> insertEmotion(@MemberId Long memberId, @Valid @RequestBody EmotionRequestDTO requestDTO) {
        emotionService.insertEmotion(memberId, requestDTO);
        return ApiResponse.OK;
    }

    @ApiOperation("감정 삭제")
    @Auth
    @DeleteMapping ("/emotion/{recordId}")
    public ApiResponse<String> deleteEmotion(@MemberId Long memberId, @PathVariable Long recordId) {
        emotionService.deleteEmotion(memberId, recordId);
        return ApiResponse.OK;
    }

    @ApiOperation("감정 일기 기준 조회")
    @Auth
    @GetMapping ("/emotion/{recordId}")
    public ApiResponse<List<EmotionResponseDTO>> findByRecordEmotion(@MemberId Long memberId, @PathVariable Long recordId) {
        return ApiResponse.success(emotionService.findByRecordEmotion(recordId));
    }

}
