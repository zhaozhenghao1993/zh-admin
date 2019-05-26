package net.zhenghao.zh.common.dao;

import net.zhenghao.zh.common.entity.SysLogEntity;
import net.zhenghao.zh.common.vo.ChartVO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统日志
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年11月22日 下午1:29:17
 * SysLogMapper.java
 */
@MapperScan
@Component
public interface SysLogMapper extends BaseMapper<SysLogEntity> {
	
	int batchRemoveAll(Integer type);

	int selectTotalVisitCount();

	int selectTodayVisitCount();

	List<ChartVO> selectLastWeekVisitCount();
}
