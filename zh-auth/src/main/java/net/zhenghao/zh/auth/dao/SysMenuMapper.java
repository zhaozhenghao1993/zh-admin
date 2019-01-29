package net.zhenghao.zh.auth.dao;

import net.zhenghao.zh.common.dao.BaseMapper;
import net.zhenghao.zh.auth.entity.SysMenuEntity;
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
public interface SysMenuMapper extends BaseMapper<SysMenuEntity>{

	List<SysMenuEntity> listParentId(Long parentId);
	
	/**
	 * 得到非按钮的菜单
	 * @return
	 */
	List<SysMenuEntity> listNotButton();
	
	/**
	 * 得到该用户在这个菜单下的按钮权限
	 * @param userId
	 * @return
	 */
	List<String> listUserPerms(Long userId);

	/**
	 * 得到该用户所有菜单
	 * @param userId
	 * @return
	 */
	List<SysMenuEntity> listUserMenu(Long userId);
	
}
