package com.samples.s3.fileupload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Rest interface which exposes all API to manage the files with minio S3
 */
@Slf4j
@RestController
public class FileUploadController {

    @Autowired
    private ContentManager s3Client;


    @GetMapping(value = "/uploadFileAndGetLocation")
    public String uploadFileAndGetLocation() {
        log.info("Inside uploadFile");
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/zipkin.PNG");
        } catch (FileNotFoundException e) {
            log.error("Error", e);
        }
        String fileUrl = s3Client.uploadContent(file);
        return fileUrl;
    }
}
