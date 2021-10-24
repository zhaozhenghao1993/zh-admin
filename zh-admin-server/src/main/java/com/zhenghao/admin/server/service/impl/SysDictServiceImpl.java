package com.zhenghao.admin.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.zhenghao.admin.api.entity.SysDictItem;
import com.zhenghao.admin.common.entity.Page;
import com.zhenghao.admin.common.entity.Query;
import com.zhenghao.admin.common.entity.Result;
import com.zhenghao.admin.common.enums.StatusTypeEnum;
import com.zhenghao.admin.common.util.CommonUtils;
import com.zhenghao.admin.server.dao.SysDictItemMapper;
import com.zhenghao.admin.server.dao.SysDictMapper;
import com.zhenghao.admin.server.entity.SysDictEntity;
import com.zhenghao.admin.server.entity.SysDictItemEntity;
import com.zhenghao.admin.server.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2021/10/23 21:00
 * SysDictServiceImpl.java
 */
@Service
public class SysDictServiceImpl implements SysDictService {

    private final SysDictMapper sysDictMapper;

    private final SysDictItemMapper sysDictItemMapper;

    @Autowired
    public SysDictServiceImpl(SysDictMapper sysDictMapper, SysDictItemMapper sysDictItemMapper) {
        this.sysDictMapper = sysDictMapper;
        this.sysDictItemMapper = sysDictItemMapper;
    }

    @Override
    public Result<Page<SysDictEntity>> listSysDict(Map<String, Object> params) {
        Query query = new Query(params);
        Page<SysDictEntity> page = new Page<>(query);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        page.setData(sysDictMapper.listForPage(query));
        return CommonUtils.msg(page);
    }

    @Override
    public Result<SysDictEntity> getSysDictById(Long id) {
        SysDictEntity sysDict = sysDictMapper.getObjectById(id);
        return CommonUtils.msg(sysDict);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result saveSysDict(SysDictEntity sysDict) {
        if (sysDictMapper.getSysDictByCode(sysDict.getCode()) != null) {
            return Result.ofFail("该数据字典编码已存在");
        }
        int count = sysDictMapper.save(sysDict);
        return CommonUtils.msg(count);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result updateSysDict(SysDictEntity sysDict) {
        SysDictEntity sysDictEntity = sysDictMapper.getSysDictByCode(sysDict.getCode());
        if (sysDictEntity != null && !sysDictEntity.getId().equals(sysDict.getId())) {
            return Result.ofFail("该数据字典编码已存在");
        }
        SysDictEntity oldSysDict = sysDictMapper.getObjectById(sysDict.getId());
        // 编码发生变化就修改字典项的字典编码
        if (!sysDict.getCode().equals(oldSysDict.getCode())) {
            sysDictItemMapper.updateDictCode(oldSysDict.getCode(), sysDict.getCode());
        }
        int count = sysDictMapper.update(sysDict);
        return CommonUtils.msg(count);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result removeSysDict(Long id) {
        SysDictEntity sysDict = sysDictMapper.getObjectById(id);
        if (sysDictItemMapper.existDictItemByDictCode(sysDict.getCode()) != null) {
            return Result.ofFail("该数据字典还存在字典项，请先删除");
        }
        int count = sysDictMapper.remove(id);
        return CommonUtils.msg(count);
    }

    @Override
    public Result<Page<SysDictItemEntity>> listSysDictItem(Map<String, Object> params, String dictCode) {
        Query query = new Query(params);
        query.put("dictCode", dictCode);
        Page<SysDictItemEntity> page = new Page<>(query);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        page.setData(sysDictItemMapper.listForPage(query));
        return CommonUtils.msg(page);
    }

    @Override
    public Result<SysDictItemEntity> getSysDictItemById(Long id) {
        SysDictItemEntity sysDictItem = sysDictItemMapper.getObjectById(id);
        return CommonUtils.msg(sysDictItem);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result saveSysDictItem(SysDictItemEntity sysDictItem) {
        SysDictEntity sysDict = sysDictMapper.getSysDictByCode(sysDictItem.getDictCode());
        if (sysDict == null) {
            return Result.ofFail("该数据字典编码不存在，请检查");
        }
        int count = sysDictItemMapper.save(sysDictItem);
        return CommonUtils.msg(count);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result updateSysDictItem(SysDictItemEntity sysDictItem) {
        int count = sysDictItemMapper.update(sysDictItem);
        return CommonUtils.msg(count);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result removeSysDictItem(Long id) {
        int count = sysDictItemMapper.remove(id);
        return CommonUtils.msg(count);
    }

    @Override
    public List<SysDictItem> listSysDictItems(String dictCode) {
        return sysDictItemMapper.listSysDictItems(dictCode, StatusTypeEnum.ENABLE.getValue());
    }
}