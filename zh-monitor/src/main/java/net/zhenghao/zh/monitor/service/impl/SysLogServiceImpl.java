package net.zhenghao.zh.monitor.service.impl;

import com.github.pagehelper.PageHelper;
import net.zhenghao.zh.common.dao.SysLogMapper;
import net.zhenghao.zh.common.entity.*;
import net.zhenghao.zh.common.utils.CommonUtils;
import net.zhenghao.zh.common.utils.DateUtils;
import net.zhenghao.zh.common.vo.ChartVO;
import net.zhenghao.zh.monitor.service.SysLogService;
import net.zhenghao.zh.monitor.vo.VisitCountVO;
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
 * @date  :2017年11月22日 下午2:08:50
 * SysLogServiceImpl.java
 */
@Service("sysLogService")
@Transactional
public class SysLogServiceImpl implements SysLogService {

	@Autowired
	private SysLogMapper sysLogMapper;
	
	@Override
	public Page<SysLogEntity> listLog(Map<String, Object> params) {
		Query query = new Query(params);
		Page<SysLogEntity> page = new Page<>(query);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		page.setData(sysLogMapper.listForPage(query));
		return page;
	}

	@Override
	public Result batchRemove(Long[] ids) {
		int count = sysLogMapper.batchRemove(ids);
		return CommonUtils.msg(ids, count);
	}

	@Override
	public Result batchRemoveAll(Integer type) {
		int count = sysLogMapper.batchRemoveAll(type);
		return CommonUtils.msg(count);
	}

	@Override
	public Result<VisitCountVO> visitCount() {
		VisitCountVO visit = new VisitCountVO();
		visit.setTotalVisitCount(sysLogMapper.selectTotalVisitCount());
		visit.setTodayVisitCount(sysLogMapper.selectTodayVisitCount());
		return CommonUtils.msg(visit);
	}

	@Override
	public Result<List<ChartVO>> lastWeekVisitCount() {
		List<ChartVO> chartVOs = new ArrayList<>();
		String[] lastWeek = DateUtils.getBeforeSevenDay();
		List<ChartVO> charts = sysLogMapper.selectLastWeekVisitCount();
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
