package net.zhenghao.zh.auth.handler;

import net.zhenghao.zh.common.config.UploadConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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

    public String avatarHandler(MultipartFile file) {
        // 如果没有文件上传就 return null
        if (file == null) {
            return null;
        }
        return "";
    }
}
