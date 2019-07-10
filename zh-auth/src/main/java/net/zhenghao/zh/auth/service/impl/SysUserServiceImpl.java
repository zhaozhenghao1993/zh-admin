package net.zhenghao.zh.auth.service.impl;

import com.github.pagehelper.PageHelper;
import net.zhenghao.zh.auth.dao.*;
import net.zhenghao.zh.auth.entity.SysMenuEntity;
import net.zhenghao.zh.auth.entity.SysOrgEntity;
import net.zhenghao.zh.auth.entity.SysUserEntity;
import net.zhenghao.zh.auth.handler.UserAvatarHandler;
import net.zhenghao.zh.common.constant.SystemConstant;
import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.common.entity.Query;
import net.zhenghao.zh.common.entity.Result;
import net.zhenghao.zh.common.util.CommonUtils;
import net.zhenghao.zh.common.util.MD5Utils;
import net.zhenghao.zh.auth.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019年2月17日 上午9:10:21
 * SysUserServiceImpl.java
 */
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

    private static final String USER_ID = "userId";

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysPostMapper sysPostMapper;

    @Autowired
    private SysOrgMapper sysOrgMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysUserPostMapper sysUserPostMapper;

    @Autowired
    private UserAvatarHandler userAvatarHandler;

    @Override
    public List<SysMenuEntity> listUserPerms(Long id) {
        return sysMenuMapper.listUserPerms(id);
    }

    @Override
    public SysUserEntity getUserByName(String username) {
        return sysUserMapper.getByUserName(username);
    }

    @Override
    public Page<SysUserEntity> listUser(Map<String, Object> params) {
        Query query = new Query(params);
        Page<SysUserEntity> page = new Page<>(query);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        page.setData(sysUserMapper.listForPage(query));
        return page;
    }

    @Override
    public Result<SysUserEntity> getUserInfo(Long id) {
        SysUserEntity user = sysUserMapper.getObjectById(id);
        user.setRoles(sysRoleMapper.listUserRoles(id));
        user.setPerms(sysMenuMapper.listUserMenu(id));
        user.setPosts(sysPostMapper.listUserPosts(id));
        // 设置组织列表
        if (user.getOrgId() != null && user.getOrgId() != 0L) {
            SysOrgEntity org = sysOrgMapper.getObjectById(user.getOrgId());
            user.setOrgs(sysOrgMapper.listByIds(org.getAncestors() + ',' + user.getOrgId()));
        }
        return CommonUtils.msg(user);
    }

    @Override
    public Result profileUser(SysUserEntity user) {
        if (StringUtils.isBlank(user.getName())) {
            return Result.ofFail("The name cannot be empty !");
        }
        int count = sysUserMapper.update(user);
        return CommonUtils.msg(count);
    }

    @Override
    public Result profileAvatar(SysUserEntity user, MultipartFile file) {
        user.setAvatar(userAvatarHandler.avatarHandler(user.getId(), file));
        int count = sysUserMapper.update(user);
        return CommonUtils.msg(count);
    }

    @Override
    public Result saveUser(SysUserEntity user) {
        if (StringUtils.isBlank(user.getUsername())) {
            return Result.ofFail("The username cannot be empty !");
        }
        if (StringUtils.isBlank(user.getName())) {
            return Result.ofFail("The name cannot be empty !");
        }
        if (StringUtils.isBlank(user.getPassword())) {
            return Result.ofFail("The password cannot be empty !");
        }
        if (sysUserMapper.countByUserName(user.getUsername()) > 0) {
            return Result.ofFail("The username already exists !");
        }
        user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
        int count = sysUserMapper.save(user);
        if (user.getRoleIdList() != null && !user.getRoleIdList().isEmpty()) {
            Query query = new Query();
            query.put(USER_ID, user.getId());
            query.put("roleIdList", user.getRoleIdList());
            sysUserRoleMapper.save(query);
        }
        if (user.getPostIdList() != null && !user.getPostIdList().isEmpty()) {
            Query query = new Query();
            query.put(USER_ID, user.getId());
            query.put("postIdList", user.getPostIdList());
            sysUserPostMapper.save(query);
        }
        return CommonUtils.msg(count);
    }

    @Override
    public Result<SysUserEntity> getUserById(Long id) {
        SysUserEntity user = sysUserMapper.getObjectById(id);
        List<Long> roleId = sysUserRoleMapper.listUserRoleId(id);
        List<Long> postId = sysUserPostMapper.listUserPostId(id);
        user.setRoleIdList(roleId);
        user.setPostIdList(postId);
        return CommonUtils.msg(user);
    }

    @Override
    public Result updateUser(SysUserEntity user) {
        if (StringUtils.isBlank(user.getUsername())) {
            return Result.ofFail("The username cannot be empty !");
        }
        SysUserEntity userEntity = sysUserMapper.getByUserName(user.getUsername());
        if (userEntity != null && userEntity.getId() != user.getId()) {
            return Result.ofFail("The username already exists !");
        }
        int count = sysUserMapper.update(user);
        Long id = user.getId();
        sysUserRoleMapper.removeByUserId(id);
        if (user.getRoleIdList() != null && !user.getRoleIdList().isEmpty()) {
            Query query = new Query();
            query.put(USER_ID, id);
            query.put("roleIdList", user.getRoleIdList());
            sysUserRoleMapper.save(query);
        }
        sysUserPostMapper.removeByUserId(id);
        if (user.getPostIdList() != null && !user.getPostIdList().isEmpty()) {
            Query query = new Query();
            query.put(USER_ID, id);
            query.put("postIdList", user.getPostIdList());
            sysUserPostMapper.save(query);
        }
        return CommonUtils.msg(count);
    }

    @Override
    public Result removeUser(Long id) {
        if (id == 1L) {
            return Result.ofFail("admin不能删除!");
        }
        int count = sysUserMapper.remove(id);
        sysUserRoleMapper.removeByUserId(id);
        sysUserPostMapper.removeByUserId(id);
        return CommonUtils.msg(count);
    }

    @Override
    public Result batchRemove(Long[] ids) {
        if (Arrays.stream(ids).anyMatch(SystemConstant.SUPER_ADMIN::equals)) {
            return Result.ofFail("包含admin不能删除!");
        }
        int count = sysUserMapper.batchRemove(ids);
        sysUserRoleMapper.batchRemoveByUserId(ids);
        sysUserPostMapper.batchRemoveByUserId(ids);
        return CommonUtils.msg(ids, count);
    }

    @Override
    public Result updatePasswordByUser(SysUserEntity user) {
        String username = user.getUsername();
        String password = user.getPassword();
        String newPassword = user.getEmail();
        password = MD5Utils.encrypt(username, password);
        newPassword = MD5Utils.encrypt(username, newPassword);
        Query query = new Query();
        query.put("id", user.getId());
        query.put("password", password);
        query.put("newPassword", newPassword);
        int count = sysUserMapper.updatePasswordByUser(query);
        if (!CommonUtils.isIntThanZero(count)) {
            return Result.ofFail("原密码错误");
        }
        return CommonUtils.msg(count);
    }

    @Override
    public Result updateUserEnable(Long[] ids) {
        Query query = new Query();
        query.put("array", ids);
        query.put("status", SystemConstant.StatusType.ENABLE.getValue());
        int count = sysUserMapper.updateUserStatus(query);
        return CommonUtils.msg(ids, count);
    }

    @Override
    public Result updateUserDisable(Long[] ids) {
        Query query = new Query();
        query.put("array", ids);
        query.put("status", SystemConstant.StatusType.DISABLE.getValue());
        int count = sysUserMapper.updateUserStatus(query);
        return CommonUtils.msg(ids, count);
    }

    @Override
    public Result updatePassword(SysUserEntity user) {
        if (StringUtils.isBlank(user.getPassword())) {
            return Result.ofFail("The password cannot be empty !");
        }
        SysUserEntity currUser = sysUserMapper.getObjectById(user.getId());
        user.setPassword(MD5Utils.encrypt(currUser.getUsername(), user.getPassword()));
        int count = sysUserMapper.updatePassword(user);
        return CommonUtils.msg(count);
    }

    @Override
    public Result updateThemeById(SysUserEntity user) {
        int count = sysUserMapper.updateThemeById(user);
        return CommonUtils.msg(count);
    }

    @Override
    public Result updateColorById(SysUserEntity user) {
        int count = sysUserMapper.updateColorById(user);
        return CommonUtils.msg(count);
    }

}
