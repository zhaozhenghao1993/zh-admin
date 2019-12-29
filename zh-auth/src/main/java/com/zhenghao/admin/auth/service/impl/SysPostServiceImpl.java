package com.zhenghao.admin.auth.service.impl;

import com.github.pagehelper.PageHelper;
import com.zhenghao.admin.auth.dao.SysPostMapper;
import com.zhenghao.admin.auth.dao.SysUserPostMapper;
import com.zhenghao.admin.auth.entity.SysPostEntity;
import com.zhenghao.admin.auth.service.SysPostService;
import com.zhenghao.admin.common.entity.Page;
import com.zhenghao.admin.common.entity.Query;
import com.zhenghao.admin.common.entity.Result;
import com.zhenghao.admin.common.util.CommonUtils;
import org.apache.commons.lang3.StringUtils;
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
 * @date :2019/04/27 22:16
 * SysPostServiceImpl.java
 */
@Service
@Transactional
public class SysPostServiceImpl implements SysPostService {

    private final SysPostMapper sysPostMapper;

    private final SysUserPostMapper sysUserPostMapper;

    @Autowired
    public SysPostServiceImpl(SysPostMapper sysPostMapper, SysUserPostMapper sysUserPostMapper) {
        this.sysPostMapper = sysPostMapper;
        this.sysUserPostMapper = sysUserPostMapper;
    }

    @Override
    public Result<Page<SysPostEntity>> listPost(Map<String, Object> params) {
        Query query = new Query(params);
        Page<SysPostEntity> page = new Page<>(query);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        page.setData(sysPostMapper.listForPage(query));
        return CommonUtils.msg(page);
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
        if (StringUtils.isBlank(post.getPostName())) {
            return Result.ofFail("The post name cannot be empty !");
        }
        if (StringUtils.isBlank(post.getPostCode())) {
            return Result.ofFail("The post code cannot be empty !");
        }
        int count = sysPostMapper.save(post);
        return CommonUtils.msg(count);
    }

    @Override
    public Result updatePost(SysPostEntity post) {
        if (StringUtils.isBlank(post.getPostName())) {
            return Result.ofFail("The post name cannot be empty !");
        }
        if (StringUtils.isBlank(post.getPostCode())) {
            return Result.ofFail("The post code cannot be empty !");
        }
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