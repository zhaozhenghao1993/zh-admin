package net.zhenghao.zh.monitor.service.impl;

import com.github.pagehelper.PageHelper;
import net.zhenghao.zh.common.dao.SysLogMapper;
import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.common.entity.Query;
import net.zhenghao.zh.common.entity.R;
import net.zhenghao.zh.common.entity.SysLogEntity;
import net.zhenghao.zh.common.utils.CommonUtils;
import net.zhenghao.zh.monitor.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 系统日志
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年11月22日 下午2:08:50
 * SysLogServiceImpl.java
 */
@Service("SysLogService")
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
	public R batchRemove(Long[] ids) {
		int count = sysLogMapper.batchRemove(ids);
		return CommonUtils.msg(ids, count);
	}

	@Override
	public R batchRemoveAll(Integer type) {
		int count = sysLogMapper.batchRemoveAll(type);
		return CommonUtils.msg(count);
	}

}
