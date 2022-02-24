package com.hh.record.service.upload;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.hh.record.config.exception.errorCode.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
@Service
public class UploadServiceImpl implements UploadService {

    private final S3Service s3Service;

    public String imageUpload(MultipartFile file) {
        UploadUtils.validateFileType(file.getOriginalFilename());
        String fileName = UploadUtils.createFileNameAndDirectory(file.getOriginalFilename());

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        try (InputStream inputStream = file.getInputStream()) {
            return s3Service.uploadFile(inputStream, objectMetadata, fileName);
        } catch (IOException e) {
            throw new ValidationException(String.format("%s 파일을 업로드하는데 오류가 발생했습니다.", file.getOriginalFilename()));
        }
    }

    @Override
    public void imageRemove(String fileUrl) {
        try {
            s3Service.removeFile(fileUrl);
        } catch (Exception e) {
            throw new ValidationException(String.format("%s 파일을 삭제하는데 오류가 발생했습니다.", fileUrl));
        }
    }

}
