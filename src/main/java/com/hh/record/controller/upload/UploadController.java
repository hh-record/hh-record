package com.hh.record.controller.upload;

import com.hh.record.controller.ApiResponse;
import com.hh.record.dto.file.FileRemoveRequestDTO;
import com.hh.record.service.upload.UploadService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hh-record")
public class UploadController {

    private final UploadService uploadService;

    @ApiOperation(value = "이미지 업로드", notes = "이미지 파일 업로드")
    @PostMapping("/record-file")
    public ApiResponse<String> imageUpload(@RequestPart MultipartFile file) {
        return ApiResponse.success(uploadService.imageUpload(file));
    }

    // todo 이미지 삭제를 할 필요가 있나요?
    @ApiOperation(value = "이미지 삭제", notes = "이미지 파일 삭제")
    @DeleteMapping(value = "/record-file", produces = "application/json")
    public ApiResponse<String> imageRemove(@RequestBody FileRemoveRequestDTO requestDTO) {
        uploadService.imageRemove(requestDTO.getFileUrl());
        return ApiResponse.OK;
    }

}

