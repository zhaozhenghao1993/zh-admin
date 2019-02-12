package net.zhenghao.zh.auth.controller;

import net.zhenghao.zh.common.jwt.JWTInfo;
import net.zhenghao.zh.common.utils.JWTTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private JWTTokenUtils jwtTokenUtils;

    @GetMapping("/demo")
    public String demo() {
        return "success";
    }

    @GetMapping("/getToken")
    public String getToken() throws Exception {
        return jwtTokenUtils.generateToken(new JWTInfo(1L, "admin"));
    }

    @PostMapping("/auth")
    public String auth() {
        return "auth => success";
    }
}
