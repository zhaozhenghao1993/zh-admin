package com.zhenghao.admin.monitor.controller;

import com.zhenghao.admin.common.entity.Result;
import com.zhenghao.admin.common.util.CommonUtils;
import com.zhenghao.admin.monitor.entity.ServerBaseEntity;
import com.zhenghao.admin.monitor.entity.ServerInstantEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 🙃
 * 🙃 性能监控controller
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/04/07 13:37
 * ServerMonitorController.java
 */
@Controller
@RequestMapping("${zh-admin.api.prefix}/monitor/performance")
public class MonitorPerformanceController {

    @Value("${zh-admin.api.prefix}")
    private String apiPrefix;

    private static final String forwardPath = "forward:/actuator/metrics/";

    private static List<String> systemParamsList = new ArrayList<>();

    private static List<String> jvmParamsList = new ArrayList<>();

    private static List<String> tomcatParamsList = new ArrayList<>();

    static {
        systemParamsList.add("system.cpu.count");
        systemParamsList.add("system.cpu.usage");
        systemParamsList.add("process.start.time");
        systemParamsList.add("process.uptime");
        systemParamsList.add("process.cpu.usage");

        jvmParamsList.add("jvm.memory.max");
        jvmParamsList.add("jvm.memory.committed");
        jvmParamsList.add("jvm.memory.used");
        jvmParamsList.add("jvm.buffer.memory.used");
        jvmParamsList.add("jvm.buffer.count");
        jvmParamsList.add("jvm.threads.daemon");
        jvmParamsList.add("jvm.threads.live");
        jvmParamsList.add("jvm.threads.peak");
        jvmParamsList.add("jvm.classes.loaded");
        jvmParamsList.add("jvm.classes.unloaded");
        jvmParamsList.add("jvm.gc.memory.allocated");
        jvmParamsList.add("jvm.gc.memory.promoted");
        jvmParamsList.add("jvm.gc.max.data.size");
        jvmParamsList.add("jvm.gc.live.data.size");
        jvmParamsList.add("jvm.gc.pause");

        tomcatParamsList.add("tomcat.sessions.created");
        tomcatParamsList.add("tomcat.sessions.expired");
        tomcatParamsList.add("tomcat.sessions.active.current");
        tomcatParamsList.add("tomcat.sessions.active.max");
        tomcatParamsList.add("tomcat.sessions.rejected");
        tomcatParamsList.add("tomcat.global.sent");
        tomcatParamsList.add("tomcat.global.request.max");
        tomcatParamsList.add("tomcat.global.request");
        tomcatParamsList.add("tomcat.servlet.request");
        tomcatParamsList.add("tomcat.servlet.request.max");
        tomcatParamsList.add("tomcat.threads.current");
        tomcatParamsList.add("tomcat.threads.config.max");
    }

    @GetMapping("/system/{actuator}")
    public String system(@PathVariable String actuator) {
        String forwardApi = "forward:" + apiPrefix + "/monitor/performance/error";
        if (validateChain(systemParamsList, actuator)) {
            forwardApi = forwardPath + actuator;
        }
        return forwardApi;
    }

    @GetMapping("/jvm/{actuator}")
    public String jvm(@PathVariable String actuator) {
        String forwardApi = "forward:" + apiPrefix + "/monitor/performance/error";
        if (validateChain(jvmParamsList, actuator)) {
            forwardApi = forwardPath + actuator;
        }
        return forwardApi;
    }

    @GetMapping("/tomcat/{actuator}")
    public String tomcat(@PathVariable String actuator) {
        String forwardApi = "forward:" + apiPrefix + "/monitor/performance/error";
        if (validateChain(tomcatParamsList, actuator)) {
            forwardApi = forwardPath + actuator;
        }
        return forwardApi;
    }

    @GetMapping("/error")
    @ResponseBody
    public Result<String> error() {
        return Result.ofFail("当前监控请求参数无授权");
    }

    private boolean validateChain (List<String> paramsList, String actuator) {
        return paramsList.stream().anyMatch(actuator::equals);
    }
}
