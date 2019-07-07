package net.zhenghao.zh.auth.service.impl;

import com.github.pagehelper.PageHelper;
import net.zhenghao.zh.auth.dao.SysRoleMapper;
import net.zhenghao.zh.auth.dao.SysRoleMenuMapper;
import net.zhenghao.zh.auth.dao.SysUserRoleMapper;
import net.zhenghao.zh.common.constant.SystemConstant;
import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.common.entity.Query;
import net.zhenghao.zh.common.entity.Result;
import net.zhenghao.zh.common.util.CommonUtils;
import net.zhenghao.zh.auth.entity.SysRoleEntity;
import net.zhenghao.zh.auth.service.SysRoleService;
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

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public Page<SysRoleEntity> listRole(Map<String, Object> params) {
        Query query = new Query(params);
        Page<SysRoleEntity> page = new Page<>(query);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        page.setData(sysRoleMapper.listForPage(query));
        return page;
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
