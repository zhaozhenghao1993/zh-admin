package com.zhenghao.admin.auth.controller;

import com.zhenghao.admin.auth.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.zhenghao.admin.common.constant.SystemConstant.API_PREFIX;

@RestController
@RequestMapping(API_PREFIX + "/test")
public class TestController {

    private final InetUtils inetUtils;

    @Autowired
    public TestController(InetUtils inetUtils) {
        this.inetUtils = inetUtils;
    }

    @GetMapping("/heart")
    public String heart() {
        return "success";
    }

    @GetMapping("/ip")
    public String test() {
        String ip = inetUtils.findFirstNonLoopbackHostInfo().getIpAddress();
        return ip;
    }

    @GetMapping("/exception")
    public String exception() {
        SysUserEntity user = null;
        return user.getName();
    }

}
