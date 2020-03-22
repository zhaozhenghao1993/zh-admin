package com.zhenghao.admin.auth.controller;

import com.zhenghao.admin.auth.entity.SysRoleEntity;
import com.zhenghao.admin.auth.service.SysRoleService;
import com.zhenghao.admin.common.annotation.SysLog;
import com.zhenghao.admin.common.constant.SystemConstant;
import com.zhenghao.admin.common.controller.AbstractController;
import com.zhenghao.admin.common.entity.Page;
import com.zhenghao.admin.common.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static com.zhenghao.admin.common.constant.SystemConstant.API_PREFIX;

/**
 * 系统角色controller
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019年2月17日 下午2:48:50
 * SysRoleController.java
 */
@RestController
@RequestMapping(API_PREFIX + "/sys/role")
public class SysRoleController extends AbstractController {

    private final SysRoleService sysRoleService;

    @Autowired
    public SysRoleController(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    @GetMapping("")
    public Result<Page<SysRoleEntity>> list(@RequestParam Map<String, Object> params) {
        if (getUserId() != SystemConstant.SUPER_ADMIN) {
            params.put("creatorId", getUserId());
        }
        return sysRoleService.listRole(params);
    }

    /**
     * 根据id查询详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<SysRoleEntity> info(@PathVariable("id") Long id) {
        return sysRoleService.getRoleById(id);
    }

    /**
     * 角色list
     *
     * @return
     */
    @GetMapping("/select")
    public Result<List<SysRoleEntity>> listRole() {
        return sysRoleService.listRole();
    }

    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    @SysLog("新增角色")
    @PostMapping("")
    public Result saveRole(@RequestBody @Valid SysRoleEntity role, BindingResult results) {
        if (results.hasErrors()) {
            return Result.ofFail(results.getFieldError().getDefaultMessage());
        }
        role.setCreatorId(getUserId());
        return sysRoleService.saveRole(role);
    }

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    @SysLog("修改角色")
    @PutMapping("/{id}")
    public Result updateRole(@PathVariable("id") Long id, @RequestBody @Valid SysRoleEntity role, BindingResult results) {
        if (results.hasErrors()) {
            return Result.ofFail(results.getFieldError().getDefaultMessage());
        }
        role.setId(id);
        role.setModifierId(getUserId());
        return sysRoleService.updateRole(role);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @SysLog("删除角色")
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable("id") Long id) {
        return sysRoleService.removeRole(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @SysLog("批量删除角色")
    @DeleteMapping("")
    public Result batchRemove(@RequestBody Long[] ids) {
        return sysRoleService.batchRemove(ids);
    }

    /**
     * 分配权限
     *
     * @param id
     * @param menuIdList
     * @return
     */
    @SysLog(value = "分配权限", type = SystemConstant.LogType.AUTHORIZATION)
    @PutMapping("/{id}/authorize")
    public Result updateRoleAuthorization(@PathVariable("id") Long id, @RequestBody List<Long> menuIdList) {
        return sysRoleService.updateRoleAuthorization(id, menuIdList);
    }
}
