package net.zhenghao.zh.auth.service;

import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.auth.entity.SysPostEntity;
import net.zhenghao.zh.common.entity.Result;

import java.util.List;
import java.util.Map;

/**
 * 岗位管理
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2019/04/27 22:16
 * SysPostService.java
 */
public interface SysPostService {

	Page<SysPostEntity> listPost(Map<String, Object> params);

    Result<SysPostEntity> getPostById(Long id);

	Result<List<SysPostEntity>> listPost();

	Result savePost(SysPostEntity post);

	Result updatePost(SysPostEntity post);

	Result removePost(Long id);

	Result batchRemove(Long[] ids);
	
}