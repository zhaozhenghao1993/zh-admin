package com.zhenghao.admin.auth.handler;

import com.zhenghao.admin.common.config.UploadConfig;
import com.zhenghao.admin.common.constant.SystemConstant;
import com.zhenghao.admin.common.constant.UploadConstant;
import com.zhenghao.admin.common.exception.upload.UploadException;
import com.zhenghao.admin.common.exception.upload.UploadSizeException;
import com.zhenghao.admin.common.exception.upload.UploadTypeException;
import com.zhenghao.admin.common.util.FileUtils;
import com.zhenghao.admin.common.util.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 🙃
 * 🙃 用户头像处理类
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/05/13 22:43
 * UserAvatarHandler.java
 */

@Component
public class UserAvatarHandler {

    @Autowired
    private UploadConfig uploadConfig;

    /**
     * 头像上传处理
     *
     * @param userId 用户id
     * @param file
     * @return
     */
    public String avatarHandler(Long userId, MultipartFile file) {
        // 如果没有文件上传就 return 默认头像
        if (file == null) {
            return UploadConstant.USER_AVATAR_DEFAULT_PATH;
        }
        if (FileUtils.fileType(file.getOriginalFilename()) != SystemConstant.FileType.IMAGE) {
            throw new UploadTypeException("Please upload images!");
        }
        if (!FileUtils.checkFileSize(file.getSize(), UploadConstant.USER_AVATAR_FILE_SIZE, UploadConstant.USER_AVATAR_FILE_SIZE_UNIT)) {
            throw new UploadSizeException("Upload file too large!");
        }
        String fileName = UploadConstant.USER_AVATAR_FILE_NAME + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
        String folderPath = uploadConfig.getFolder() + UploadConstant.USER_AVATAR_FOLDER + File.separator + userId;
        String filePath = uploadConfig.getPath() + UploadConstant.USER_AVATAR_FOLDER + userId  + fileName;
        try {
            UploadUtils.uploadFile(file, folderPath, fileName);
        } catch (IOException e) {
            throw new UploadException("Upload file Error!");
        }
        return filePath;
    }
}
