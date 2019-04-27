package net.zhenghao.zh.auth.dao;

import net.zhenghao.zh.common.dao.BaseMapper;
import net.zhenghao.zh.auth.entity.SysPostEntity;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

/**
 * 岗位管理
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2019/04/27 22:16
 * SysPostMapper.java
 */
@MapperScan
@Component
public interface SysPostMapper extends BaseMapper<SysPostEntity> {
	
}