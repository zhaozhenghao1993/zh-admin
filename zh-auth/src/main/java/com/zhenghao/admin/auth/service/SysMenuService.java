package com.zhenghao.admin.auth.service;

import com.zhenghao.admin.auth.entity.SysMenuEntity;
import com.zhenghao.admin.common.entity.Page;
import com.zhenghao.admin.common.entity.Result;
import com.zhenghao.admin.common.vo.TreeVO;

import java.util.List;
import java.util.Map;

/**
 * 系统菜单
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019年1月29日 上午10:43:52
 * SysMenuService.java
 */
public interface SysMenuService {

    Result<Page<SysMenuEntity>> listMenu(Map<String, Object> params);

    Result<List<TreeVO>> listTree(Map<String, Object> params);

    Result saveMenu(SysMenuEntity menu);

    Result<SysMenuEntity> getMenuById(Long id);

    Result updateMenu(SysMenuEntity menu);

    Result remove(Long id);

    Result batchRemove(Long[] ids);

}
