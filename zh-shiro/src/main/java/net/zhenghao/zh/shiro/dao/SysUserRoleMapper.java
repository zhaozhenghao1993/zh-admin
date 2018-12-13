package net.zhenghao.zh.shiro.dao;

import net.zhenghao.zh.common.dao.BaseMapper;
import net.zhenghao.zh.shiro.entity.SysUserRoleEntity;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * 用户与角色关系
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年12月6日 上午11:13:36
 * SysUserRoleMapper.java
 */
@MapperScan
public interface SysUserRoleMapper extends BaseMapper<SysUserRoleEntity> {

	/**
	 * 用户id下所有角色id
	 * @param userId
	 * @return
	 */
	List<Long> listUserRoleId(Long userId);
	
	int batchRemoveByUserId(Long[] id);
	
	int batchRemoveByRoleId(Long[] id);
	
}
