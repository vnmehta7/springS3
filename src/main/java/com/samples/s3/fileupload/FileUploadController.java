package com.samples.s3.fileupload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@Slf4j
@RestController
public class FileUploadController {

    @Autowired
    private ContentManager s3Client;
    private String bucketName = "bucket";

    @GetMapping(value = "/uploadFileAndGetLocation")
    public String uploadFileAndGetLocation() {
        log.info("Inside uploadFile");
        String fileUrl = s3Client.uploadFile(new File("C:\\workspace\\zipkin.PNG"), bucketName);

        return fileUrl;
    }
}
