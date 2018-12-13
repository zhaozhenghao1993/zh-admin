package net.zhenghao.zh.shiro.dao;

import net.zhenghao.zh.common.dao.BaseMapper;
import net.zhenghao.zh.shiro.entity.SysRoleEntity;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * 系统角色
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年12月6日 上午11:23:20
 * SysRoleMapper.java
 */
@MapperScan
public interface SysRoleMapper extends BaseMapper<SysRoleEntity>{

	/**
	 * 根据用户id查询该用户的角色标识
	 * @param userId
	 * @return
	 */
	List<String> listUserRoles(Long userId);
}
