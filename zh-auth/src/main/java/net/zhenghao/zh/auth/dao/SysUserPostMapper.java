package net.zhenghao.zh.auth.dao;

import net.zhenghao.zh.common.dao.BaseMapper;
import net.zhenghao.zh.auth.entity.SysUserPostEntity;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户岗位关联表
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/05/01 12:38
 * SysUserPostMapper.java
 */
@MapperScan
@Component
public interface SysUserPostMapper extends BaseMapper<SysUserPostEntity> {

    /**
     * 用户id下所有角色id
     *
     * @param userId
     * @return
     */
    List<Long> listUserPostId(Long userId);

    int removeByUserId(Long id);

    int removeByPostId(Long id);

    int batchRemoveByUserId(Long[] ids);

    int batchRemoveByPostId(Long[] ids);
}