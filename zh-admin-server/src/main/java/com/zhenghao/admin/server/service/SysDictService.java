package com.zhenghao.admin.server.service;

import com.zhenghao.admin.api.service.SysDictItemApiService;
import com.zhenghao.admin.common.entity.Page;
import com.zhenghao.admin.common.entity.Result;
import com.zhenghao.admin.server.entity.SysDictEntity;
import com.zhenghao.admin.server.entity.SysDictItemEntity;

import java.util.Map;

/**
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2021/10/23 21:00
 * SysDictService.java
 */
public interface SysDictService extends SysDictItemApiService {

    Result<Page<SysDictEntity>> listSysDict(Map<String, Object> params);

    Result<SysDictEntity> getSysDictById(Long id);

    Result saveSysDict(SysDictEntity sysDict);

    Result updateSysDict(SysDictEntity sysDict);

    Result removeSysDict(Long id);

    Result<Page<SysDictItemEntity>> listSysDictItem(Map<String, Object> params, String dictCode);

    Result<SysDictItemEntity> getSysDictItemById(Long id);

    Result saveSysDictItem(SysDictItemEntity sysDictItem);

    Result updateSysDictItem(SysDictItemEntity sysDictItem);

    Result removeSysDictItem(Long id);

}