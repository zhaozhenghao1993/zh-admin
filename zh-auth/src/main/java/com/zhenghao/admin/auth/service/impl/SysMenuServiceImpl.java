package com.zhenghao.admin.auth.service.impl;

import com.zhenghao.admin.auth.dao.SysMenuMapper;
import com.zhenghao.admin.auth.dao.SysRoleMenuMapper;
import com.zhenghao.admin.auth.entity.SysMenuEntity;
import com.zhenghao.admin.auth.enums.MenuTypeEnum;
import com.zhenghao.admin.auth.service.SysMenuService;
import com.zhenghao.admin.common.constant.SystemConstant;
import com.zhenghao.admin.common.entity.Page;
import com.zhenghao.admin.common.entity.Query;
import com.zhenghao.admin.common.entity.Result;
import com.zhenghao.admin.common.util.CommonUtils;
import com.zhenghao.admin.common.util.TreeUtils;
import com.zhenghao.admin.common.vo.TreeVO;
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
@Transactional
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public Page<SysMenuEntity> listMenu(Map<String, Object> params) {
        Query query = new Query(params);
        Page<SysMenuEntity> page = new Page<>(query);
        query.removePageParams(); // 不分页，删除分页参数
        page.setData(TreeUtils.build(sysMenuMapper.list(query), SystemConstant.TREE_ROOT));
        return page;
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
            listRoot = TreeUtils.build(sysMenuMapper.listTree(), SystemConstant.TREE_ROOT);
        }
        return CommonUtils.msgNotNull(listRoot);
    }

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

    @Override
    public Result updateMenu(SysMenuEntity menu) {
        int count = sysMenuMapper.update(menu);
        return CommonUtils.msg(count);
    }

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

    @Override
    public Result batchRemove(Long[] ids) {
        int count = sysMenuMapper.batchRemove(ids);
        sysRoleMenuMapper.batchRemoveByMenuId(ids);
        return CommonUtils.msg(ids, count);
    }

}
