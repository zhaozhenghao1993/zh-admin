package net.zhenghao.zh.monitor.controller;

import net.zhenghao.zh.common.annotation.SysLog;
import net.zhenghao.zh.common.controller.AbstractController;
import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.common.entity.Result;
import net.zhenghao.zh.common.entity.SysLogEntity;
import net.zhenghao.zh.common.vo.ChartVO;
import net.zhenghao.zh.monitor.service.SysLogService;
import net.zhenghao.zh.monitor.vo.VisitCountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统日志监控
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2019年04月11日 下午2:01:26
 * SysLogController.java
 */
@RestController
@RequestMapping("${zh-admin.api.prefix}/monitor/log")
public class MonitorLogController extends AbstractController {

	@Autowired
	private SysLogService sysLogService;
	
	/**
	 * 日志列表
	 * @param params
	 * @return
	 */
	@GetMapping("")
	public Page<SysLogEntity> listLog(@RequestParam Map<String, Object> params){
		return sysLogService.listLog(params);
	}
	
	/**
	 * 删除日志
	 * @param ids
	 * @return
	 */
	@SysLog("批量删除日志")
	@DeleteMapping("")
	public Result batchRemove(@RequestBody Long[] ids){
		return sysLogService.batchRemove(ids);
	}
	
	/**
	 * 清空日志
	 * @return
	 */
	@SysLog("清空日志")
	@DeleteMapping("/{type}/clear")
	public Result batchRemoveAll(@PathVariable("type") Integer type){
		return sysLogService.batchRemoveAll(type);
	}

	@GetMapping("/visit")
	public Result<VisitCountVO> visitCount(){
		return sysLogService.visitCount();
	}

	@GetMapping("/visit/week")
	public Result<List<ChartVO>> lastWeekVisitCount(){
		return sysLogService.lastWeekVisitCount();
	}
}
