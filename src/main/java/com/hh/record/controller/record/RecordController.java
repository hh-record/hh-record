package com.hh.record.controller.record;

import com.hh.record.config.interceptor.Auth;
import com.hh.record.config.interceptor.MemberId;
import com.hh.record.controller.ApiResponse;
import com.hh.record.dto.record.*;
import com.hh.record.dto.theme.ThemeInfoResponse;
import com.hh.record.service.record.RecordService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hh-record")
public class RecordController {

    private final RecordService recordService;

    @ApiOperation("메인 일기 목록 조회")
    @Auth
    @GetMapping(value = "/records-main")
    public ApiResponse<List<RecordResponseDTO>> selectRecordList(@MemberId Long memberId) {
        return ApiResponse.success(recordService.selectRecordList(memberId));
    }

    @ApiOperation("검색 일기 목록 조회")
    @Auth
    @PostMapping(value = "/records-main")
    public ApiResponse<List<RecordResponseDTO>> retrieveRecord(@MemberId Long memberId, @RequestBody RecordSearchRequestDTO requestDTO) {
        return ApiResponse.success(recordService.retrieveRecord(memberId, requestDTO));
    }

    @ApiOperation("달력 일기 목록 조회")
    @Auth
    @GetMapping("/records-calendar")
    public ApiResponse<List<LocalDate>> selectRecord(@MemberId Long memberId, @Valid RecordCalendarRequestDTO requestDTO) {
        return ApiResponse.success(recordService.selectRecordDate(memberId, requestDTO));
    }

    @ApiOperation("오늘의 주제 불러오기")
    @Auth
    @GetMapping("/records-theme")
    public ApiResponse<ThemeInfoResponse> getTheme() {
        return ApiResponse.success(recordService.selectTheme());
    }

    @ApiOperation("일기 생성")
    @Auth
    @PostMapping("/records")
    public ApiResponse<RecordResponseDTO> createRecord(@MemberId Long memberId, @Valid @RequestBody CreateRecordRequestDto requestDto) {
        return ApiResponse.success(recordService.createRecord(memberId, requestDto));
    }

    @ApiOperation("일기 상세 조회")
    @Auth
    @PostMapping("/records/{recordId}")
    public ApiResponse<RecordResponseDTO> selectOneRecord(@MemberId Long memberId, @PathVariable("recordId") Long recordId) {
        return ApiResponse.success(recordService.selectOneRecord(memberId, recordId));
    }

    @ApiOperation("일기 수정")
    @Auth
    @PutMapping(value = "/records/{recordId}", produces = "application/json")
    public ApiResponse<Long> updateRecord(@MemberId Long memberId, @PathVariable("recordId") Long recordId,
                                          @Valid @RequestBody RecordUpdateRequestDTO requestDTO) {
        return ApiResponse.success(recordService.updateRecord(memberId, recordId, requestDTO));
    }

    @ApiOperation("일기 이미지 수정")
    @Auth
    @PutMapping(value = "/records/file/{recordId}", produces = "application/json")
    public ApiResponse<RecordResponseDTO> updateRecordFile(@MemberId Long memberId, @PathVariable("recordId") Long recordId,
                                          @RequestBody RecordUpdateFileRequestDto requestDto) {
        return ApiResponse.success(recordService.updateRecordFile(memberId, recordId, requestDto.getFileList()));
    }

    @ApiOperation("일기 삭제")
    @Auth
    @DeleteMapping("/records/{recordId}")
    public ApiResponse<Long> deleteRecord(@MemberId Long memberId, @PathVariable("recordId") Long recordId) {
        return ApiResponse.success(recordService.deleteRecord(memberId, recordId));
    }

}
