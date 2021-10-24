package com.zhenghao.admin.server.handler.avatar;

import com.zhenghao.admin.common.constant.SystemConstants;
import com.zhenghao.admin.common.constant.UploadConstants;
import com.zhenghao.admin.common.enums.FileTypeEnum;
import com.zhenghao.admin.common.exception.upload.UploadException;
import com.zhenghao.admin.common.exception.upload.UploadSizeException;
import com.zhenghao.admin.common.exception.upload.UploadTypeException;
import com.zhenghao.admin.common.util.FileUtils;
import com.zhenghao.admin.server.config.UploadConfig;
import com.zhenghao.admin.server.util.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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

    private final UploadConfig uploadConfig;

    @Autowired
    public UserAvatarHandler(UploadConfig uploadConfig) {
        this.uploadConfig = uploadConfig;
    }

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
            return UploadConstants.USER_AVATAR_DEFAULT_PATH;
        }
        if (FileUtils.fileType(file.getOriginalFilename()) != FileTypeEnum.IMAGE) {
            throw new UploadTypeException("Please upload images!");
        }
        if (!FileUtils.checkFileSize(file.getSize(), UploadConstants.USER_AVATAR_FILE_SIZE, UploadConstants.USER_AVATAR_FILE_SIZE_UNIT)) {
            throw new UploadSizeException("Upload file too large!");
        }
        String fileName = UploadConstants.USER_AVATAR_FILE_NAME.concat(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.')));
        String folderPath = uploadConfig.getFolder().concat(UploadConstants.USER_AVATAR_FOLDER).concat(userId.toString());
        String filePath = uploadConfig.getPath().concat(UploadConstants.USER_AVATAR_FOLDER).concat(userId.toString()).concat(UploadConstants.PATH_SEPARATOR).concat(fileName);
        try {
            UploadUtils.uploadFile(file, folderPath, fileName);
        } catch (IOException e) {
            throw new UploadException("Upload file Error!");
        }
        return filePath;
    }
}
