package com.zhenghao.admin.monitor.controller;

import com.zhenghao.admin.common.entity.Result;
import com.zhenghao.admin.common.util.CommonUtils;
import com.zhenghao.admin.monitor.entity.ServerBaseEntity;
import com.zhenghao.admin.monitor.entity.ServerInstantEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.zhenghao.admin.common.constant.SystemConstant.API_PREFIX;

/**
 * 🙃
 * 🙃 服务器监控controller
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/04/07 13:37
 * ServerMonitorController.java
 */
@RestController
@RequestMapping(API_PREFIX + "/monitor/server")
public class MonitorServerController {

    private final ServerBaseEntity base;

    private final ServerInstantEntity instant;

    @Autowired
    public MonitorServerController(ServerBaseEntity base, ServerInstantEntity instant) {
        this.base = base;
        this.instant = instant;
    }

    @GetMapping("/base")
    public Result<ServerBaseEntity> baseInfo() {
        base.init();
        return CommonUtils.msg(base);
    }

    @GetMapping("/instant")
    public Result<ServerInstantEntity> instantInfo() {
        instant.init();
        return CommonUtils.msg(instant);
    }
}
