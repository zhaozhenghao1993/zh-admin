package com.zhenghao.admin.common.dao;

import com.zhenghao.admin.common.entity.Query;
import com.zhenghao.admin.common.entity.Query;

import java.util.List;

/**
 * 基础dao
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年11月22日 上午11:47:09
 * BaseMapper.java
 */
public interface BaseMapper<T> {

    /**
     * 新增
     *
     * @param t
     * @return
     */
    int save(T t);

    /**
     * 新增
     *
     * @param query
     * @return
     */
    int save(Query query);

    /**
     * 批量新增
     *
     * @param items
     * @return
     */
    int batchSave(List<T> items);

    /**
     * 查询详情(查询一条)
     *
     * @param t
     * @return
     */
    T getObject(T t);

    /**
     * 根据id查询详情
     *
     * @param id
     * @return
     */
    T getObjectById(Object id);

    /**
     * 更新
     *
     * @param t
     * @return
     */
    int update(T t);

    /**
     * 更新
     *
     * @param query
     * @return
     */
    int update(Query query);

    /**
     * 批量更新
     *
     * @param query
     * @return
     */
    int batchUpdate(Query query);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int remove(Object id);

    /**
     * 逻辑删除
     *
     * @param id
     * @return
     */
    int removeLogic(Object id);

    /**
     * 批量删除
     *
     * @param id
     * @return
     */
    int batchRemove(Object[] id);

    /**
     * 批量逻辑删除
     *
     * @param id
     * @return
     */
    int batchRemoveLogic(Object[] id);

    /**
     * 分页查询列表
     *
     * @param query
     * @return
     */
    List<T> listForPage(Query query);

    /**
     * 查询列表(带查询条件)
     *
     * @param query
     * @return
     */
    List<T> list(Query query);

    /**
     * 查询列表
     *
     * @return
     */
    List<T> list();

    /**
     * 统计
     *
     * @return
     */
    int countTotal();

    /**
     * 统计(带查询条件)
     *
     * @param query
     * @return
     */
    int countTotal(Query query);
}
