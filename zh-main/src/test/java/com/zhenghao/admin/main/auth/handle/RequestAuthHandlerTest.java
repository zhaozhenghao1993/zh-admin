package com.zhenghao.admin.main.auth.handle;

import com.zhenghao.admin.auth.handler.RequestAuthHandler;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestAuthHandlerTest {

    @Autowired
    private RequestAuthHandler requestAuthHandler;

    @Test
    public void validateAnnoFilterChain() {
        Assert.assertTrue("可以直接匿名访问uri",
                requestAuthHandler.validateAnnoFilterChain("/sys/login", "POST"));
    }

    @Test
    public void validateAuthFilterChain() {
        Assert.assertTrue("需要登陆才能访问uri",
                requestAuthHandler.validateAuthFilterChain("/sys/user/info", "GET"));
    }

    @Test
    public void validatePermsFilterChain() {
        Assert.assertTrue("需要授权才能访问uri",
                requestAuthHandler.validatePermsFilterChain("/sys/user", "POST", 1L));
    }

    @Test
    public void validate() {
        Assert.assertTrue(
                "验证restful风格uri",
                RequestAuthHandler.validate(
                        "/sys/user/1/reset",
                        "PUT",
                        "/sys/user/{id}/reset",
                        "PUT")
        );

        Assert.assertTrue(
                "验证通配符风格uri",
                RequestAuthHandler.validate(
                        "/druid/wall.html",
                        "GET",
                        "/druid/*",
                        "GET")
        );

        Assert.assertTrue(
                "综合验证uri",
                RequestAuthHandler.validate(
                        "/uploads/-/system/user/avatar/1/avatar.jpg",
                        "GET",
                        "/uploads/-/system/user/avatar/{id}/*",
                        "GET")
        );
    }
}