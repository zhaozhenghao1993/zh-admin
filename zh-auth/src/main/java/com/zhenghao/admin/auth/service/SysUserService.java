package com.zhenghao.admin.auth.service;

import com.zhenghao.admin.auth.entity.SysMenuEntity;
import com.zhenghao.admin.auth.entity.SysUserEntity;
import com.zhenghao.admin.common.entity.Page;
import com.zhenghao.admin.common.entity.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年12月7日 上午9:07:06
 * SysUserService.java
 */
public interface SysUserService {

    /**
     * 获取用户的权限filter chain
     *
     * @param id
     * @return
     */
    List<SysMenuEntity> listUserPerms(Long id);

    SysUserEntity getUserByName(String username);

    Result<Page<SysUserEntity>> listUser(Map<String, Object> params);

    Result<SysUserEntity> getUserInfo(Long id);

    Result profileUser(SysUserEntity user);

    Result profileAvatar(SysUserEntity user, MultipartFile file);

    Result saveUser(SysUserEntity user);

    Result<SysUserEntity> getUserById(Long id);

    Result updateUser(SysUserEntity user);

    Result removeUser(Long id);

    Result batchRemove(Long[] ids);

    Result updatePasswordByUser(SysUserEntity user);

    Result updateUserEnable(Long[] ids);

    Result updateUserDisable(Long[] ids);

    Result updatePassword(SysUserEntity user);

    Result updateThemeById(SysUserEntity user);

    Result updateColorById(SysUserEntity user);

}
