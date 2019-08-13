package com.zhenghao.admin.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${zh-admin.api.prefix}/test")
public class TestController {

    @Autowired
    private InetUtils inetUtils;

    @GetMapping("/heart")
    public String heart() {
        return "success";
    }

    @GetMapping("/ip")
    public String test() {
        String ip = inetUtils.findFirstNonLoopbackHostInfo().getIpAddress();
        return ip;
    }


}
