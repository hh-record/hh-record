package com.hh.record.controller.upload;

import com.hh.record.controller.ApiResponse;
import com.hh.record.dto.file.FileRemoveRequestDTO;
import com.hh.record.service.upload.UploadFolder;
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

    @ApiOperation(value = "이미지 업로드", notes = "일기, 멤버 이미지 파일 업로드")
    @PostMapping("/record-file/{uploadFolder}")
    public ApiResponse<String> recordImageUpload(@RequestPart MultipartFile file, @PathVariable UploadFolder uploadFolder) {
        System.out.println("file : " + file);
        System.out.println("uploadFolder : " + uploadFolder);
        return ApiResponse.success(uploadService.imageUpload(file, uploadFolder));
    }

    // todo 이미지 삭제를 할 필요가 있나요? // 있지 않을까여?? - minHong
    @ApiOperation(value = "이미지 삭제", notes = "이미지 파일 삭제")
    @DeleteMapping(value = "/record-file/{uploadFolder}", produces = "application/json")
    public ApiResponse<String> imageRemove(@RequestBody FileRemoveRequestDTO requestDTO, @PathVariable UploadFolder uploadFolder) {
        uploadService.imageRemove(requestDTO.getFileUrl(), uploadFolder);
        return ApiResponse.OK;
    }

}

