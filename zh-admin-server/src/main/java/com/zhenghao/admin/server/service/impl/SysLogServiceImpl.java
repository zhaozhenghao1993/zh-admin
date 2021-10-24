package com.zhenghao.admin.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.zhenghao.admin.common.entity.Page;
import com.zhenghao.admin.common.entity.Query;
import com.zhenghao.admin.common.entity.Result;
import com.zhenghao.admin.common.util.CommonUtils;
import com.zhenghao.admin.common.util.DateUtils;
import com.zhenghao.admin.common.vo.ChartVO;
import com.zhenghao.admin.server.dao.SysLogMapper;
import com.zhenghao.admin.server.entity.SysLogEntity;
import com.zhenghao.admin.server.entity.vo.VisitCountVO;
import com.zhenghao.admin.server.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 系统日志
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年11月22日 下午2:08:50
 * SysLogServiceImpl.java
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    private final SysLogMapper sysLogMapper;

    @Autowired
    public SysLogServiceImpl(SysLogMapper sysLogMapper) {
        this.sysLogMapper = sysLogMapper;
    }

    @Override
    public Result<Page<SysLogEntity>> listLog(Map<String, Object> params) {
        Query query = new Query(params);
        Page<SysLogEntity> page = new Page<>(query);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        page.setData(sysLogMapper.listForPage(query));
        return CommonUtils.msg(page);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result batchRemove(Long[] ids) {
        int count = sysLogMapper.batchRemove(ids);
        return CommonUtils.msg(ids, count);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result batchRemoveAll(Integer type) {
        int count = sysLogMapper.batchRemoveAll(type);
        return CommonUtils.msg(count);
    }

    @Override
    public Result<VisitCountVO> countVisit() {
        VisitCountVO visit = new VisitCountVO();
        visit.setTotalVisitCount(sysLogMapper.countTotalVisit());
        visit.setTodayVisitCount(sysLogMapper.countTodayVisit());
        return CommonUtils.msg(visit);
    }

    @Override
    public Result<List<ChartVO>> countLastWeekVisit() {
        List<ChartVO> chartVOs = new ArrayList<>();
        String[] lastWeek = DateUtils.getBeforeSevenDay();
        List<ChartVO> charts = sysLogMapper.listCountLastWeekVisit();
        ChartVO chart;
        for (int i = 0; i < lastWeek.length; i++) {
            boolean bool = true;
            for (int j = 0; j < charts.size(); j++) {
                if (charts.get(j).getX().equals(lastWeek[i])) {
                    bool = false;
                    chartVOs.add(charts.get(j));
                    break;
                }
            }
            if (bool) {
                chart = new ChartVO();
                chart.setX(lastWeek[i]);
                chart.setY(0);
                chartVOs.add(chart);
            }
        }
        return CommonUtils.msg(chartVOs);
    }

}
