package com.zhenghao.admin.server.service;

import com.zhenghao.admin.common.entity.Page;
import com.zhenghao.admin.common.entity.Result;
import com.zhenghao.admin.common.vo.ChartVO;
import com.zhenghao.admin.server.entity.SysLogEntity;
import com.zhenghao.admin.server.entity.vo.VisitCountVO;

import java.util.List;
import java.util.Map;

/**
 * 系统日志service层
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年11月22日 下午2:05:02
 * SysLogService.java
 */
public interface SysLogService {

    Result<Page<SysLogEntity>> listLog(Map<String, Object> params);

    Result batchRemove(Long[] ids);

    Result batchRemoveAll(Integer type);

    Result<VisitCountVO> countVisit();

    Result<List<ChartVO>> countLastWeekVisit();
}
