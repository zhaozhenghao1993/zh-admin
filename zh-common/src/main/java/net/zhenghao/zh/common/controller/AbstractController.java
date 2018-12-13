package net.zhenghao.zh.common.controller;

import net.zhenghao.zh.common.entity.SysUserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller公共组件
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年11月22日 下午3:03:45
 * AbstractController.java
 */
public abstract class AbstractController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected SysUserEntity getUser(){
		return new SysUserEntity();
	}

	protected Long getUserId(){
		return getUser().getUserId();
	}
}
