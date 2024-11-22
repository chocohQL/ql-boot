package com.qilicoh.boot.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

public class OssClient {
    @Value("${oss.endpoint}")
    private String endpoint;
    @Value("${oss.url}")
    private String url;
    @Value("${oss.access-key-id}")
    private String accessKeyId;
    @Value("${oss.access-key-secret}")
    private String accessKeySecret;
    @Value("${oss.bucket-name}")
    private String bucketName;
    @Value("${oss.file-dir}")
    private String fileDir;

    private static final List<String> IMG_EXTENSIONS = Arrays.asList(
            "png", "jpg", "jpeg", "bmp" ,"gif", "webp", "svg"
    );

    public String uploadAvatar(MultipartFile file) throws IOException {
        OSS ossClient = createOss();
        try {
            if (file != null) {
                String originalFilename = file.getOriginalFilename();
                if (originalFilename != null) {
                    String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                    if (isImg(extension)) {
                        String filename = fileDir + UUID.randomUUID() + extension;
                        ossClient.putObject(bucketName, filename, file.getInputStream());
                        return url + filename;
                    }
                }
            }
            return null;
        } finally {
            shutdownOss(ossClient);
        }
    }

    public boolean deleteAvatar(String filename) {
        OSS ossClient = createOss();
        try {
            if (filename == null) {
                return false;
            }
            String realFilename = filename.replace(url, "");
            if (!ossClient.doesObjectExist(bucketName, realFilename)) {
                return false;
            } else {
                ossClient.deleteObject(bucketName, realFilename);
                return true;
            }
        } finally {
            shutdownOss(ossClient);
        }
    }

    private OSS createOss() {
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    private void shutdownOss(OSS occClient) {
        occClient.shutdown();
    }

    private boolean isImg(String extension) {
        return IMG_EXTENSIONS.contains(extension.toLowerCase().replace(".", ""));
    }
}
