package net.zhenghao.zh.monitor.controller;

import net.zhenghao.zh.common.entity.R;
import net.zhenghao.zh.common.utils.CommonUtils;
import net.zhenghao.zh.monitor.entity.ServerBaseEntity;
import net.zhenghao.zh.monitor.entity.ServerInstantEntity;
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
@RequestMapping("/monitor/server")
public class ServerMonitorController {

    @GetMapping("/base")
    public R baseInfo() {
        ServerBaseEntity base = new ServerBaseEntity();
        base.init();
        return CommonUtils.msg(base);
    }

    @GetMapping("/instant")
    public R instantInfo() {
        ServerInstantEntity instant = new ServerInstantEntity();
        instant.init();
        return CommonUtils.msg(instant);
    }
}
