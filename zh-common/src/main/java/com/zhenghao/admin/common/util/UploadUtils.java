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
     * @param folderPath 上传文件的路径
     * @return
     * @throws IOException
     */
    public static String uploadFile(MultipartFile file, String folderPath) throws IOException {
        return uploadFile(file, folderPath, file.getOriginalFilename());
    }

    /**
     * 上传文件
     *
     * @param file
     * @param folderPath 上传文件的路径
     * @param fileName   上传文件名
     * @return
     * @throws IOException
     */
    public static String uploadFile(MultipartFile file, String folderPath, String fileName) throws IOException {
        File fileFolder = new File(folderPath);
        if (!fileFolder.exists() && !fileFolder.isDirectory()) {
            fileFolder.mkdirs();
        }
        String uploadFilePath = folderPath + File.separator + fileName;
        File uploadFile = new File(uploadFilePath);
        file.transferTo(uploadFile);
        return uploadFilePath;
    }
}
