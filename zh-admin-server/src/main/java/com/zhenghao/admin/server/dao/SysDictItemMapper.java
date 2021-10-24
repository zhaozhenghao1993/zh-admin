package com.zhenghao.admin.server.dao;

import com.zhenghao.admin.api.entity.SysDictItem;
import com.zhenghao.admin.common.dao.BaseMapper;
import com.zhenghao.admin.server.entity.SysDictItemEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数据字典项目
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2021/10/23 22:00
 * SysDictItemMapper.java
 */
public interface SysDictItemMapper extends BaseMapper<SysDictItemEntity> {

    /**
     * 更新字典编码
     * @param oldDictCode
     * @param newDictCode
     * @return
     */
    int updateDictCode(@Param("oldDictCode") String oldDictCode, @Param("newDictCode") String newDictCode);

    Integer existDictItemByDictCode(String dictCode);

    /**
     * 获取有效的数据字典元素
     * @param dictCode
     * @param status 0 正常， 1禁用
     * @return
     */
    List<SysDictItem> listSysDictItems(@Param("dictCode") String dictCode, @Param("status") Integer status);
}