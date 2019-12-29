package com.zhenghao.admin.common.dao;

import com.zhenghao.admin.common.entity.SysLogEntity;
import com.zhenghao.admin.common.vo.ChartVO;

import java.util.List;

/**
 * 系统日志
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年11月22日 下午1:29:17
 * SysLogMapper.java
 */
public interface SysLogMapper extends BaseMapper<SysLogEntity> {

    int batchRemoveAll(Integer type);

    int countTotalVisit();

    int countTodayVisit();

    List<ChartVO> listCountLastWeekVisit();
}
