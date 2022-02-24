package com.hh.record.service.upload;

import com.hh.record.config.exception.errorCode.ValidationException;
import org.apache.commons.io.FilenameUtils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class UploadUtils {

    public static void validateFileType(String originalFilename) {
        final List<String> typeList = Arrays.asList("jpg", "jpg", "jpeg", "png");
        String type = FilenameUtils.getExtension(originalFilename);
        if (!typeList.contains(type)) {
            throw new ValidationException(String.format("%s는 허용되지 않는 파일형식입니다.", type));
        }
    }

    public static String createFolder(UploadFolder uploadFolder) {
        return uploadFolder + "/";
    }

    public static String createFileNameAndDirectory(String originalFilename) {
        String now = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());
        final String folder = createFolder(UploadFolder.USER);
        return folder.concat(now.concat(originalFilename));
    }
}
