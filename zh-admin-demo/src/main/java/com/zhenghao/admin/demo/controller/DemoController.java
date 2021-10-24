package com.zhenghao.admin.demo.controller;

import com.zhenghao.admin.api.entity.SysDictItem;
import com.zhenghao.admin.api.service.SysDictItemApiService;
import com.zhenghao.admin.common.entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 🙃
 * 🙃
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2021/10/24 19:39
 * DemoController.java
 */

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    private final SysDictItemApiService sysDictItemApiService;

    public DemoController(SysDictItemApiService sysDictItemApiService) {
        this.sysDictItemApiService = sysDictItemApiService;
    }

    @GetMapping("/dict/{dictCode}")
    public Result<List<SysDictItem>> listDictItem(@PathVariable("dictCode") String dictCode) {
        return Result.ofSuccess(sysDictItemApiService.listSysDictItems(dictCode));
    }
}
