package net.zhenghao.zh.auth.service.impl;

import com.github.pagehelper.PageHelper;
import net.zhenghao.zh.auth.dao.SysPostMapper;
import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.common.entity.Query;
import net.zhenghao.zh.common.entity.R;
import net.zhenghao.zh.common.utils.CommonUtils;
import net.zhenghao.zh.auth.entity.SysPostEntity;
import net.zhenghao.zh.auth.service.SysPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Override
	public Page<SysPostEntity> listPost(Map<String, Object> params) {
		Query query = new Query(params);
		Page<SysPostEntity> page = new Page<>(query);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        page.setData(sysPostMapper.listForPage(query));
		return page;
	}

    @Override
    public R getPostById(Long postId) {
		SysPostEntity sysPost = sysPostMapper.getObjectById(postId);
        return CommonUtils.msg(sysPost);
    }

	@Override
	public R savePost(SysPostEntity post) {
		int count = sysPostMapper.save(post);
		return CommonUtils.msg(count);
	}

	@Override
	public R updatePost(SysPostEntity post) {
		int count = sysPostMapper.update(post);
		return CommonUtils.msg(count);
	}

    @Override
    public R removePost(Long postId) {
        int count = sysPostMapper.remove(postId);
        return CommonUtils.msg(count);
    }

	@Override
	public R batchRemove(Long[] ids) {
		int count = sysPostMapper.batchRemove(ids);
		return CommonUtils.msg(ids, count);
	}
	
}