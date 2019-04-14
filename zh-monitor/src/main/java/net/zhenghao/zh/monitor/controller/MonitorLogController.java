package net.zhenghao.zh.monitor.controller;

import net.zhenghao.zh.common.annotation.SysLog;
import net.zhenghao.zh.common.controller.AbstractController;
import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.common.entity.R;
import net.zhenghao.zh.common.entity.SysLogEntity;
import net.zhenghao.zh.monitor.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/monitor/log")
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
	@SysLog("删除日志")
	@DeleteMapping("")
	public R batchRemove(@RequestBody Long[] ids){
		return sysLogService.batchRemove(ids);
	}
	
	/**
	 * 清空日志
	 * @return
	 */
	@SysLog("清空日志")
	@DeleteMapping("/{type}/clear")
	public R batchRemoveAll(@PathVariable("type") Integer type){
		return sysLogService.batchRemoveAll(type);
	}
}
