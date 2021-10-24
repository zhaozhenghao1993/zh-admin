package com.zhenghao.admin.server.service.impl;

import com.zhenghao.admin.common.constant.SystemConstants;
import com.zhenghao.admin.common.entity.Page;
import com.zhenghao.admin.common.entity.Query;
import com.zhenghao.admin.common.entity.Result;
import com.zhenghao.admin.common.util.CommonUtils;
import com.zhenghao.admin.common.util.TreeUtils;
import com.zhenghao.admin.common.vo.TreeVO;
import com.zhenghao.admin.server.dao.SysMenuMapper;
import com.zhenghao.admin.server.dao.SysRoleMenuMapper;
import com.zhenghao.admin.server.entity.SysMenuEntity;
import com.zhenghao.admin.server.enums.MenuTypeEnum;
import com.zhenghao.admin.server.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 系统菜单
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年12月7日 下午2:51:12
 * SysMenuServiceImpl.java
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    private final SysMenuMapper sysMenuMapper;

    private final SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    public SysMenuServiceImpl(SysMenuMapper sysMenuMapper, SysRoleMenuMapper sysRoleMenuMapper) {
        this.sysMenuMapper = sysMenuMapper;
        this.sysRoleMenuMapper = sysRoleMenuMapper;
    }

    @Override
    public Result<Page<SysMenuEntity>> listMenu(Map<String, Object> params) {
        Query query = new Query(params);
        Page<SysMenuEntity> page = new Page<>(query);
        query.removePageParams(); // 不分页，删除分页参数
        page.setData(TreeUtils.build(sysMenuMapper.list(query), SystemConstants.TREE_ROOT));
        return CommonUtils.msg(page);
    }

    @Override
    public Result<List<TreeVO>> listTree(Map<String, Object> params) {
        List<TreeVO> listRoot;
        if (params.get("isNotButton") != null && "true".equals(params.get("isNotButton"))) {
            List<TreeVO> treeList = sysMenuMapper.listTreeNotButton();
            TreeVO root = new TreeVO();
            root.setId(0L);
            root.setKey(0L);
            root.setTitle("主目录");
            root.setValue("0");
            root.setParentId(-1L);
            treeList.add(root);
            listRoot = TreeUtils.build(treeList, -1L);
        } else {
            listRoot = TreeUtils.build(sysMenuMapper.listTree(), SystemConstants.TREE_ROOT);
        }
        return CommonUtils.msgNotNull(listRoot);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result saveMenu(SysMenuEntity menu) {
        if (menu.getType() == MenuTypeEnum.BUTTON.getValue() ||
                menu.getType() == MenuTypeEnum.LINK.getValue()) {
            if (!Pattern.compile("^(GET|POST|PUT|DELETE)$").matcher(menu.getMethod()).find()) {
                return Result.ofFail("请求method应为GET|POST|PUT|DELETE中的一个");
            }
        }
        int count = sysMenuMapper.save(menu);
        return CommonUtils.msg(count);
    }

    @Override
    public Result<SysMenuEntity> getMenuById(Long id) {
        SysMenuEntity menu = sysMenuMapper.getObjectById(id);
        return CommonUtils.msg(menu);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result updateMenu(SysMenuEntity menu) {
        int count = sysMenuMapper.update(menu);
        return CommonUtils.msg(count);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result remove(Long id) {
        int childCount = sysMenuMapper.countChildById(id);
        if (childCount > 0) {
            return Result.ofFail("该菜单含有子菜单,请先删除子菜单!");
        }
        int count = sysMenuMapper.remove(id);
        sysRoleMenuMapper.removeByMenuId(id);
        return CommonUtils.msg(count);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result batchRemove(Long[] ids) {
        int count = sysMenuMapper.batchRemove(ids);
        sysRoleMenuMapper.batchRemoveByMenuId(ids);
        return CommonUtils.msg(ids, count);
    }

}
