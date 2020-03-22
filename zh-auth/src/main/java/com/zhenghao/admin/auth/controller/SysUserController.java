package com.zhenghao.admin.auth.controller;

import com.zhenghao.admin.auth.dto.SysUserPasswordDTO;
import com.zhenghao.admin.auth.entity.SysUserEntity;
import com.zhenghao.admin.auth.service.SysUserService;
import com.zhenghao.admin.common.annotation.SysLog;
import com.zhenghao.admin.common.constant.SystemConstant;
import com.zhenghao.admin.common.constant.UploadConstant;
import com.zhenghao.admin.common.controller.AbstractController;
import com.zhenghao.admin.common.entity.Page;
import com.zhenghao.admin.common.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Map;

import static com.zhenghao.admin.common.constant.SystemConstant.API_PREFIX;

/**
 * 系统用户controller
 *
 * @RequestParam 用来处理Content-Type: 为 application/x-www-form-urlencoded编码的内容
 * @RequestBody 处理HttpEntity传递过来的数据，一般用来处理非Content-Type: application/x-www-form-urlencoded编码格式的数据。
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年12月8日 下午2:03:22
 * SysMenuController.java
 */
@RestController
@RequestMapping(API_PREFIX + "/sys/user")
public class SysUserController extends AbstractController {

    private final SysUserService sysUserService;

    @Autowired
    public SysUserController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    /**
     * 获取登录用户信息
     *
     * @return
     */
    @GetMapping("/info")
    public Result<SysUserEntity> info() {
        return sysUserService.getUserInfo(getUserId());
    }

    /**
     * 更新当前用户的个人基础信息
     *
     * @return
     */
    @PutMapping("/profile")
    public Result profile(@RequestBody @Valid SysUserEntity user, BindingResult results) {
        if (results.hasErrors()) {
            return Result.ofFail(results.getFieldError().getDefaultMessage());
        }
        user.setId(getUserId());
        user.setModifierId(getUserId());
        user.setStatus(null);
        return sysUserService.profileUser(user);
    }

    /**
     * 更新当前用户的头像
     *
     * @return
     */
    @PostMapping("/profile/avatar")
    public Result avatar(MultipartFile file) {
        SysUserEntity user = new SysUserEntity();
        user.setId(getUserId());
        return sysUserService.profileAvatar(user, file);
    }

    /**
     * 当前用户修改密码
     *
     * @param password
     * @return
     */
    @PutMapping("/profile/password")
    public Result updatePswdByUser(@RequestBody @Valid SysUserPasswordDTO password, BindingResult results) {
        if (results.hasErrors()) {
            return Result.ofFail(results.getFieldError().getDefaultMessage());
        }
        SysUserEntity user = new SysUserEntity();
        user.setId(getUserId());
        user.setUsername(getUserName());
        user.setPassword(password.getOldPassword()); // 原密码
        user.setEmail(password.getPassword()); // 邮箱临时存储新密码
        return sysUserService.updatePasswordByUser(user);
    }

    /**
     * 更新当前用户主题
     *
     * @return
     */
    @PutMapping("/profile/theme")
    public Result theme(@RequestBody @Valid SysUserEntity user, BindingResult results) {
        if (results.hasErrors()) {
            return Result.ofFail(results.getFieldError().getDefaultMessage());
        }
        user.setId(getUserId());
        return sysUserService.updateThemeById(user);
    }

    /**
     * 更新当前用户主题颜色
     *
     * @return
     */
    @PutMapping("/profile/color")
    public Result color(@RequestBody @Valid SysUserEntity user, BindingResult results) {
        if (results.hasErrors()) {
            return Result.ofFail(results.getFieldError().getDefaultMessage());
        }
        user.setId(getUserId());
        return sysUserService.updateColorById(user);
    }

    /**
     * 获取用户信息列表
     *
     * @return
     */
    @GetMapping("")
    public Result<Page<SysUserEntity>> list(@RequestParam Map<String, Object> params) {
        if (getUserId() != SystemConstant.SUPER_ADMIN) {
            params.put("creatorId", getUserId());
        }
        return sysUserService.listUser(params);
    }

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<SysUserEntity> info(@PathVariable("id") Long id) {
        return sysUserService.getUserById(id);
    }

    /**
     * 获取用户详细信息
     *
     * @return
     */
    @GetMapping("/{id}/detail")
    public Result<SysUserEntity> detail(@PathVariable("id") Long id) {
        return sysUserService.getUserInfo(id);
    }

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @SysLog("新增用户")
    @PostMapping("")
    public Result save(@RequestBody @Valid SysUserEntity user, BindingResult results) {
        if (results.hasErrors()) {
            return Result.ofFail(results.getFieldError().getDefaultMessage());
        }
        user.setAvatar(UploadConstant.USER_AVATAR_DEFAULT_PATH); // 给个默认头像
        user.setCreatorId(getUserId());
        return sysUserService.saveUser(user);
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @SysLog("修改用户")
    @PutMapping("/{id}")
    public Result edit(@PathVariable("id") Long id, @RequestBody @Valid SysUserEntity user, BindingResult results) {
        if (results.hasErrors()) {
            return Result.ofFail(results.getFieldError().getDefaultMessage());
        }
        user.setId(id);
        user.setModifierId(getUserId());
        return sysUserService.updateUser(user);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @SysLog("删除用户")
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable("id") Long id) {
        return sysUserService.removeUser(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @SysLog("批量删除用户")
    @DeleteMapping("")
    public Result batchRemove(@RequestBody Long[] ids) {
        return sysUserService.batchRemove(ids);
    }

    /**
     * 启用账户
     *
     * @param ids
     * @return
     */
    @SysLog("启用账号")
    @PutMapping("/enable")
    public Result updateUserEnable(@RequestBody Long[] ids) {
        return sysUserService.updateUserEnable(ids);
    }

    /**
     * 禁用账户
     *
     * @param ids
     * @return
     */
    @SysLog("锁定账户")
    @PutMapping("/disable")
    public Result updateUserDisable(@RequestBody Long[] ids) {
        return sysUserService.updateUserDisable(ids);
    }

    /**
     * 重置密码
     *
     * @param user
     * @return
     */
    @SysLog("重置密码")
    @PutMapping("/{id}/reset")
    public Result updatePswd(@PathVariable("id") Long id, @RequestBody SysUserEntity user) {
        user.setId(id);
        return sysUserService.updatePassword(user);
    }

}
