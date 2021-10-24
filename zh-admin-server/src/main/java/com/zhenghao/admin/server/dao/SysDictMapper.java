package com.zhenghao.admin.server.dao;

import com.zhenghao.admin.common.dao.BaseMapper;
import com.zhenghao.admin.server.entity.SysDictEntity;

/**
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2021/10/23 21:00
 * SysDictMapper.java
 */
public interface SysDictMapper extends BaseMapper<SysDictEntity> {

    SysDictEntity getSysDictByCode(String code);
}