package com.samples.s3.fileupload;

import java.io.File;

/**
 * Base interface which outlines all functions that ContentManager supports
 */
public interface ContentManager {
    /**
     * This method uploads content
     *
     * @param fileName
     * @return http url of uploaded content
     */
    String uploadContent(File fileName);
}
