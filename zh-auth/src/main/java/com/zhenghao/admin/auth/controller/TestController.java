package com.zhenghao.admin.auth.controller;

import com.zhenghao.admin.auth.entity.SysUserEntity;
import com.zhenghao.admin.common.annotation.SysLog;
import com.zhenghao.admin.common.jwt.JWTInfo;
import com.zhenghao.admin.common.util.JWTTokenUtils;
import com.zhenghao.admin.common.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${zh-admin.api.prefix}/test")
public class TestController {

    @Autowired
    private JWTTokenUtils jwtTokenUtils;

    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/heart")
    public String heart() {
        return "success";
    }

    @GetMapping("/getToken")
    public String getToken() {
        return jwtTokenUtils.generateToken(new JWTInfo(1L, "admin", "Administrator"));
    }

    @GetMapping("/getUserInfo")
    public JWTInfo getToken(String token) {
        return jwtTokenUtils.getInfoFromToken(token);
    }

    @PostMapping("/auth")
    public String auth() {
        return "auth => success";
    }

    @GetMapping("/set")
    public String set() {
        redisUtils.set("aa", "zhaozhenghao", 300);
        return "set => success";
    }

    @GetMapping("/get")
    public String get() {
        return (String) redisUtils.get("aa");
    }

    @GetMapping("/exception")
    @SysLog("注解日志异常测试")
    public String exception() {
        SysUserEntity user = null;
        user.getUsername();
        return "exception => success";
    }
}
