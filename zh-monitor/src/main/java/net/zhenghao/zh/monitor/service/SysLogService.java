package net.zhenghao.zh.monitor.service;

import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.common.entity.Result;
import net.zhenghao.zh.common.entity.SysLogEntity;
import net.zhenghao.zh.common.vo.ChartVO;
import net.zhenghao.zh.monitor.vo.VisitCountVO;

import java.util.List;
import java.util.Map;

/**
 * 系统日志service层
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年11月22日 下午2:05:02
 * SysLogService.java
 */
public interface SysLogService {

	Page<SysLogEntity> listLog(Map<String, Object> params);
	
	Result batchRemove(Long[] ids);

	Result batchRemoveAll(Integer type);

	Result<VisitCountVO> visitCount();

	Result<List<ChartVO>> lastWeekVisitCount();
}
