package com.samples.s3.fileupload;

import java.io.File;

public interface ContentManager {
    String uploadFile(File fileName, String fileLocation);
}
