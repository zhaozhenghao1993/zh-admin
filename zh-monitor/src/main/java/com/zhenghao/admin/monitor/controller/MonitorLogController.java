package com.zhenghao.admin.monitor.controller;

import com.zhenghao.admin.monitor.service.SysLogService;
import com.zhenghao.admin.monitor.vo.VisitCountVO;
import com.zhenghao.admin.common.annotation.SysLog;
import com.zhenghao.admin.common.controller.AbstractController;
import com.zhenghao.admin.common.entity.Page;
import com.zhenghao.admin.common.entity.Result;
import com.zhenghao.admin.common.entity.SysLogEntity;
import com.zhenghao.admin.common.vo.ChartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统日志监控
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019年04月11日 下午2:01:26
 * SysLogController.java
 */
@RestController
@RequestMapping("${zh-admin.api.prefix}/monitor/log")
public class MonitorLogController extends AbstractController {

    @Autowired
    private SysLogService sysLogService;

    /**
     * 日志列表
     *
     * @param params
     * @return
     */
    @GetMapping("")
    public Page<SysLogEntity> listLog(@RequestParam Map<String, Object> params) {
        return sysLogService.listLog(params);
    }

    /**
     * 删除日志
     *
     * @param ids
     * @return
     */
    @SysLog("批量删除日志")
    @DeleteMapping("")
    public Result batchRemove(@RequestBody Long[] ids) {
        return sysLogService.batchRemove(ids);
    }

    /**
     * 清空日志
     *
     * @return
     */
    @SysLog("清空日志")
    @DeleteMapping("/{type}/clear")
    public Result batchRemoveAll(@PathVariable("type") Integer type) {
        return sysLogService.batchRemoveAll(type);
    }

    @GetMapping("/visit")
    public Result<VisitCountVO> visitCount() {
        return sysLogService.countVisit();
    }

    @GetMapping("/visit/week")
    public Result<List<ChartVO>> lastWeekVisitCount() {
        return sysLogService.countLastWeekVisit();
    }
}
