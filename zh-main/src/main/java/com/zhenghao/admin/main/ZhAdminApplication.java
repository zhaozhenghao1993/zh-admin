package com.zhenghao.admin.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.zhenghao.admin.**"})
@MapperScan("com.zhenghao.admin.**.dao")
@EnableCaching
public class ZhAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZhAdminApplication.class, args);
    }
}
