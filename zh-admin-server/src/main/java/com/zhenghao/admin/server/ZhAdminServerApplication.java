package com.zhenghao.admin.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * 🙃
 * 🙃
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2021/10/18 23:49
 * ZhAdminServerApplication.java
 */
@SpringBootApplication(scanBasePackages = {"com.zhenghao.admin.**"})
@MapperScan("com.zhenghao.admin.**.dao")
@EnableCaching
@ServletComponentScan
public class ZhAdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhAdminServerApplication.class, args);
    }
}
