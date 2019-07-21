package com.zhenghao.admin.monitor.controller;

import com.zhenghao.admin.common.entity.Result;
import com.zhenghao.admin.common.util.CommonUtils;
import com.zhenghao.admin.monitor.entity.ServerBaseEntity;
import com.zhenghao.admin.monitor.entity.ServerInstantEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("${zh-admin.api.prefix}/monitor/server")
public class MonitorServerController {

    private static final Logger logger = LoggerFactory.getLogger(MonitorServerController.class);

    @Autowired
    private ServerBaseEntity base;

    @Autowired
    private ServerInstantEntity instant;

    @GetMapping("/base")
    public Result<ServerBaseEntity> baseInfo() {
        logger.info("MonitorServerController   base start");
        base.init();
        logger.info("MonitorServerController   base  end");
        return CommonUtils.msg(base);
    }

    @GetMapping("/instant")
    public Result<ServerInstantEntity> instantInfo() {
        logger.info("MonitorServerController   instant start");
        instant.init();
        logger.info("MonitorServerController   instant  end");
        return CommonUtils.msg(instant);
    }
}
