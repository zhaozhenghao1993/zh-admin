package com.zhenghao.admin.api.service;

import com.zhenghao.admin.api.entity.SysDictItem;

import java.util.List;

/**
 * 🙃
 * 🙃
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2021/10/23 22:20
 * SysDictItemApiService.java
 */

public interface SysDictItemApiService {

    /**
     * 根据字典编码查询对应字典项
     * @param dictCode 字典编码
     * @return
     */
    List<SysDictItem> listSysDictItems(String dictCode);
}
