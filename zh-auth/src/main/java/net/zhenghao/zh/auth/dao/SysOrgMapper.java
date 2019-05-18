package net.zhenghao.zh.auth.dao;

import net.zhenghao.zh.auth.entity.SysOrgEntity;
import net.zhenghao.zh.common.vo.TreeVO;
import net.zhenghao.zh.common.dao.BaseMapper;
import net.zhenghao.zh.common.entity.Query;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统菜单dao
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年12月6日 下午1:28:55
 * SysMenuMapper.java
 */
@MapperScan
@Component
public interface SysOrgMapper extends BaseMapper<SysOrgEntity>{

	/**
	 * 得到Tree列表
	 * @return
	 */
	List<TreeVO> listTree();

	/**
	 * 根据 OrgId 的 数组字符串查询列表
	 * @param orgIds   0,1,2
	 * @return
	 */
	List<SysOrgEntity> listByOrgIds(String orgIds);

	/**
	 * 根据menuId获取子菜单的数量
	 * @return
	 */
	int getChildCountByOrgId(Long orgId);

	/**
	 * 更新组织parentId时，需一同更新所有子组织的祖级列表
	 * @param query
	 * @return
	 */
	int updateChildAncestorsById(Query query);
	
}
