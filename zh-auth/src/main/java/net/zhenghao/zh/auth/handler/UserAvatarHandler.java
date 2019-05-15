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
            throw new BaseException("Please upload images!");
        }
        if (!FileUtils.checkFileSize(file.getSize(), UploadConstant.USER_AVATAR_FILE_SIZE, UploadConstant.USER_AVATAR_FILE_SIZE_UNIT)) {
            throw new BaseException("Upload file too large!");
        }
        String fileName = UploadConstant.USER_AVATAR_FILE_NAME + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String folderPath = uploadConfig.getFolder() + UploadConstant.USER_AVATAR_FOLDER + File.separator + userId;
        String filePath = uploadConfig.getPath() + UploadConstant.USER_AVATAR_FOLDER + "/" + userId + "/" + fileName;
        try {
            UploadUtils.uploadFile(file, folderPath, fileName);
        } catch (IOException e) {
            throw new BaseException("Upload file Error!");
        }
        return filePath;
    }
}
