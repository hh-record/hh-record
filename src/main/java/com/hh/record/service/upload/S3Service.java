package com.hh.record.service.upload;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3Client amazonS3Client;
    private final S3Component s3Component;

    public String uploadFile(InputStream inputStream, ObjectMetadata objectMetadata, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(s3Component.getBucket(), fileName, inputStream, objectMetadata)
            .withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getResourceUrl(s3Component.getBucket(), fileName);
    }

    public void removeFile(String fileUrl, UploadFolder uploadFolder) {
        amazonS3Client.deleteObject(new DeleteObjectRequest(s3Component.getBucket(), getFileKey(fileUrl, uploadFolder)));
    }

    private String getFileKey(String fileUrl, UploadFolder uploadFolder) {
        int idx = fileUrl.indexOf("/" + uploadFolder);
        return fileUrl.substring(idx + 1);
    }

}
