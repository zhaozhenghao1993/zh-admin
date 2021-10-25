package com.zhenghao.admin.common.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 🙃
 * 🙃
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/05/12 15:48
 * UploadUtils.java
 */

public class UploadUtils {

    private UploadUtils() {
    }

    /**
     * 上传文件
     *
     * @param file
     * @param directoryPath 上传文件的路径
     * @return
     * @throws IOException
     */
    public static String uploadFile(MultipartFile file, String directoryPath) throws IOException {
        return uploadFile(file, directoryPath, file.getOriginalFilename());
    }

    /**
     * 上传文件
     *
     * @param file
     * @param directoryPath 上传文件的路径
     * @param fileName   上传文件名
     * @return
     * @throws IOException
     */
    public static String uploadFile(MultipartFile file, String directoryPath, String fileName) throws IOException {
        File fileDirectory = new File(directoryPath);
        if (!fileDirectory.exists() && !fileDirectory.isDirectory()) {
            fileDirectory.mkdirs();
        }
        String uploadFilePath = directoryPath + File.separator + fileName;
        File uploadFile = new File(uploadFilePath);
        file.transferTo(uploadFile);
        return uploadFilePath;
    }
}
