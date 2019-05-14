package net.zhenghao.zh.auth.handler;

import net.zhenghao.zh.common.config.UploadConfig;
import net.zhenghao.zh.common.constant.SystemConstant;
import net.zhenghao.zh.common.constant.UploadConstant;
import net.zhenghao.zh.common.exception.BaseException;
import net.zhenghao.zh.common.utils.FileUtils;
import net.zhenghao.zh.common.utils.UploadUtils;
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
     * @param file
     * @param userId 用户id
     * @return
     */
    public String avatarHandler(MultipartFile file, Long userId) {
        // 如果没有文件上传就 return null
        if (file == null) {
            return null;
        }
        if (FileUtils.fileType(file.getName()) != SystemConstant.FileType.IMAGE) {
            throw new BaseException("Please upload images!");
        }
        if (FileUtils.checkFileSize(file.getSize(), UploadConstant.USER_AVATAR_FILE_SIZE, UploadConstant.USER_AVATAR_FILE_SIZE_UNIT)) {
            throw new BaseException("Upload file too large!");
        }
        String folderPath = uploadConfig.getFolder() + UploadConstant.USER_AVATAR_FOLDER + File.separator + userId;
        String filePath = uploadConfig.getPath() + UploadConstant.USER_AVATAR_FOLDER + File.separator + userId + File.separator + file.getName();
        try {
            UploadUtils.uploadFile(file,  folderPath);
        } catch (IOException e) {
            throw new BaseException("Upload file Error!");
        }
        return filePath;
    }
}
