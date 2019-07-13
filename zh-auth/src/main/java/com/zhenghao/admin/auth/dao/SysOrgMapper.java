package com.zhenghao.admin.auth.dao;

import com.zhenghao.admin.auth.entity.SysOrgEntity;
import com.zhenghao.admin.common.vo.TreeVO;
import com.zhenghao.admin.common.dao.BaseMapper;
import com.zhenghao.admin.common.entity.Query;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统菜单dao
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年12月6日 下午1:28:55
 * SysMenuMapper.java
 */
@MapperScan
@Component
public interface SysOrgMapper extends BaseMapper<SysOrgEntity> {

    /**
     * 得到Tree列表
     *
     * @return
     */
    List<TreeVO> listTree();

    /**
     * 根据 OrgId 的 数组字符串查询列表
     *
     * @param ids 0,1,2
     * @return
     */
    List<SysOrgEntity> listByIds(String ids);

    /**
     * 根据menuId获取子菜单的数量
     *
     * @return
     */
    int countChildById(Long orgId);

    /**
     * 更新组织parentId时，需一同更新所有子组织的祖级列表
     *
     * @param query
     * @return
     */
    int updateChildAncestorsById(Query query);

}
