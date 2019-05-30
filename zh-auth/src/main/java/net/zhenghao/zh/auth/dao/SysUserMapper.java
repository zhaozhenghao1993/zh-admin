package net.zhenghao.zh.auth.dao;

import net.zhenghao.zh.auth.entity.SysUserEntity;
import net.zhenghao.zh.common.dao.BaseMapper;
import net.zhenghao.zh.common.entity.Query;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统用户dao
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年12月6日 上午10:42:58
 * SysUserMapper.java
 */
@MapperScan
@Component
public interface SysUserMapper extends BaseMapper<SysUserEntity> {

	/**
	 * 根据 username 获取 SysUserEntity 数量
	 * @param username
	 * @return
	 */
	int getCountByUserName(String username);

	/**
	 * 根据 orgId 获取 SysUserEntity 数量
	 * @param orgId
	 * @return
	 */
	int getCountByOrgId(Long orgId);

	/**
	 * 根据 username 获取 SysUserEntity
	 * @param username
	 * @return
	 */
	SysUserEntity getByUserName(String username);
	
	/**
	 * 根据用户名密码修改密码
	 * @param query
	 * @return
	 */
	int updatePasswordByUser(Query query);
	
	/**
	 * 批量修改用户状态
	 * @param query
	 * @return
	 */
	int updateUserStatus(Query query);
	
	/**
	 * 根据用户id修改密码
	 * @param user
	 * @return
	 */
	int updatePassword(SysUserEntity user);

	/**
	 * 根据用户id修改主题
	 * @param user
	 * @return
	 */
	int updateThemeById(SysUserEntity user);

	/**
	 * 根据用户id修改主题
	 * @param user
	 * @return
	 */
	int updateColorById(SysUserEntity user);
}
