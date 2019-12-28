package com.zhenghao.admin.auth.service.impl;

import com.github.pagehelper.PageHelper;
import com.zhenghao.admin.auth.dao.SysRoleMapper;
import com.zhenghao.admin.auth.dao.SysRoleMenuMapper;
import com.zhenghao.admin.auth.dao.SysUserRoleMapper;
import com.zhenghao.admin.auth.entity.SysRoleEntity;
import com.zhenghao.admin.auth.service.SysRoleService;
import com.zhenghao.admin.common.constant.SystemConstant;
import com.zhenghao.admin.common.entity.Page;
import com.zhenghao.admin.common.entity.Query;
import com.zhenghao.admin.common.entity.Result;
import com.zhenghao.admin.common.util.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 系统角色
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年12月7日 上午10:39:00
 * SysRoleServiceImpl.java
 */
@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

    private final SysRoleMapper sysRoleMapper;

    private final SysUserRoleMapper sysUserRoleMapper;

    private final SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    public SysRoleServiceImpl(SysRoleMapper sysRoleMapper,
                              SysUserRoleMapper sysUserRoleMapper,
                              SysRoleMenuMapper sysRoleMenuMapper) {
        this.sysRoleMapper = sysRoleMapper;
        this.sysUserRoleMapper = sysUserRoleMapper;
        this.sysRoleMenuMapper = sysRoleMenuMapper;
    }

    @Override
    public Result<Page<SysRoleEntity>> listRole(Map<String, Object> params) {
        Query query = new Query(params);
        Page<SysRoleEntity> page = new Page<>(query);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        page.setData(sysRoleMapper.listForPage(query));
        return CommonUtils.msg(page);
    }

    @Override
    public Result saveRole(SysRoleEntity role) {
        if (StringUtils.isBlank(role.getRoleName())) {
            return Result.ofFail("The role name cannot be empty !");
        }
        if (StringUtils.isBlank(role.getRoleSign())) {
            return Result.ofFail("The role sign cannot be empty !");
        }
        int count = sysRoleMapper.save(role);
        return CommonUtils.msg(count);
    }

    @Override
    public Result getRoleById(Long id) {
        SysRoleEntity role = sysRoleMapper.getObjectById(id);
        List<Long> menuId = sysRoleMenuMapper.listMenuId(id);
        role.setMenuIdList(menuId);
        return CommonUtils.msg(role);
    }

    @Override
    public Result updateRole(SysRoleEntity role) {
        if (StringUtils.isBlank(role.getRoleName())) {
            return Result.ofFail("The role name cannot be empty !");
        }
        if (StringUtils.isBlank(role.getRoleSign())) {
            return Result.ofFail("The role sign cannot be empty !");
        }
        int count = sysRoleMapper.update(role);
        return CommonUtils.msg(count);
    }

    @Override
    public Result removeRole(Long id) {
        if (id == 1L) {
            return Result.ofFail("内置admin角色不能删除!");
        }
        int count = sysRoleMapper.remove(id);
        sysUserRoleMapper.removeByRoleId(id);
        sysRoleMenuMapper.removeByRoleId(id);
        return CommonUtils.msg(count);
    }

    @Override
    public Result batchRemove(Long[] ids) {
        if (Arrays.stream(ids).anyMatch(SystemConstant.SUPER_ADMIN_ROLE::equals)) {
            return Result.ofFail("包含内置admin角色不能删除!");
        }
        int count = sysRoleMapper.batchRemove(ids);
        sysUserRoleMapper.batchRemoveByRoleId(ids);
        sysRoleMenuMapper.batchRemoveByRoleId(ids);
        return CommonUtils.msg(ids, count);
    }

    @Override
    public Result<List<SysRoleEntity>> listRole() {
        List<SysRoleEntity> roleList = sysRoleMapper.list();
        return CommonUtils.msgNotNull(roleList);
    }

    @Override
    public Result updateRoleAuthorization(Long id, List<Long> menuIdList) {
        int count = sysRoleMenuMapper.removeByRoleId(id);
        if (!menuIdList.isEmpty()) {
            Query query = new Query();
            query.put("roleId", id);
            query.put("menuIdList", menuIdList);
            count = sysRoleMenuMapper.save(query);
        }
        return CommonUtils.msg(count);
    }

}
