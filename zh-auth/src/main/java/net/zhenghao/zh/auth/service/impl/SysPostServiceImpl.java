package net.zhenghao.zh.auth.service.impl;

import com.github.pagehelper.PageHelper;
import net.zhenghao.zh.auth.dao.SysPostMapper;
import net.zhenghao.zh.auth.dao.SysUserPostMapper;
import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.common.entity.Query;
import net.zhenghao.zh.common.entity.Result;
import net.zhenghao.zh.common.utils.CommonUtils;
import net.zhenghao.zh.auth.entity.SysPostEntity;
import net.zhenghao.zh.auth.service.SysPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 岗位管理
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2019/04/27 22:16
 * SysPostServiceImpl.java
 */
@Service("sysPostService")
@Transactional
public class SysPostServiceImpl implements SysPostService {

	@Autowired
	private SysPostMapper sysPostMapper;

	@Autowired
	private SysUserPostMapper sysUserPostMapper;
	
	@Override
	public Page<SysPostEntity> listPost(Map<String, Object> params) {
		Query query = new Query(params);
		Page<SysPostEntity> page = new Page<>(query);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        page.setData(sysPostMapper.listForPage(query));
		return page;
	}

    @Override
    public Result<SysPostEntity> getPostById(Long id) {
		SysPostEntity sysPost = sysPostMapper.getObjectById(id);
        return CommonUtils.msg(sysPost);
    }

	@Override
	public Result<List<SysPostEntity>> listPost() {
		List<SysPostEntity> postList = sysPostMapper.list();
		return CommonUtils.msgNotNull(postList);
	}

	@Override
	public Result savePost(SysPostEntity post) {
		int count = sysPostMapper.save(post);
		return CommonUtils.msg(count);
	}

	@Override
	public Result updatePost(SysPostEntity post) {
		int count = sysPostMapper.update(post);
		return CommonUtils.msg(count);
	}

    @Override
    public Result removePost(Long id) {
        int count = sysPostMapper.remove(id);
		sysUserPostMapper.removeByPostId(id);
        return CommonUtils.msg(count);
    }

	@Override
	public Result batchRemove(Long[] ids) {
		int count = sysPostMapper.batchRemove(ids);
		sysUserPostMapper.batchRemoveByPostId(ids);
		return CommonUtils.msg(ids, count);
	}
	
}