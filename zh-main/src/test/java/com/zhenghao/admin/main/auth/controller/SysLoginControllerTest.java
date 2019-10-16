package com.zhenghao.admin.main.auth.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zhenghao.admin.auth.vo.SysLoginVO;
import com.zhenghao.admin.common.entity.Result;
import com.zhenghao.admin.common.util.JSONUtils;
import com.zhenghao.admin.common.util.UserAuthUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SysLoginControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private UserAuthUtils userAuthUtils;

    @Value("${zh-admin.api.prefix}")
    private String apiPrefix;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void login() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("username", "admin");
        params.put("password", "123");
        // 登陆
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(apiPrefix + "/sys/login")
                .content(JSONUtils.objToString(params))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        Assert.assertEquals(mvcResult.getResponse().getStatus(), HttpServletResponse.SC_OK);
        String content = mvcResult.getResponse().getContentAsString();

        Result<SysLoginVO> result = JSONUtils.stringToObj(content, new TypeReference<Result<SysLoginVO>>() {
        });
        assert result != null;
        SysLoginVO login = result.getData();
        Assert.assertEquals(params.get("username"), userAuthUtils.getInfoFromToken(login.getToken()).getUsername());
    }
}