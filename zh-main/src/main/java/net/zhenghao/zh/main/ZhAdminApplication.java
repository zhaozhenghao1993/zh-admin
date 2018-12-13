package net.zhenghao.zh.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"net.zhenghao.zh.**"})
@MapperScan("net.zhenghao.zh.**.dao")
public class ZhAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZhAdminApplication.class, args);
    }
}
