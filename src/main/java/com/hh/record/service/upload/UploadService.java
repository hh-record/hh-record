package com.hh.record.service.upload;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    String imageUpload(MultipartFile file, UploadFolder uploadFolder);

    void imageRemove(String fileUrl, UploadFolder uploadFolder);

}
