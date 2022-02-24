package com.hh.record.service.upload;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    String imageUpload(MultipartFile file);

    void imageRemove(String fileUrl);

}
