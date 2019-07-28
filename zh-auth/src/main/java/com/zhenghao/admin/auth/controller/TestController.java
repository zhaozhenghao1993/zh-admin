package com.zhenghao.admin.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${zh-admin.api.prefix}/test")
public class TestController {

    @GetMapping("/heart")
    public String heart() {
        return "success";
    }
}
