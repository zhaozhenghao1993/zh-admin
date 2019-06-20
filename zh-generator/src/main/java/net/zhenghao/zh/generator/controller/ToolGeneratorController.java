package net.zhenghao.zh.generator.controller;

import net.zhenghao.zh.common.annotation.SysLog;
import net.zhenghao.zh.common.controller.AbstractController;
import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.common.entity.Result;
import net.zhenghao.zh.common.util.JSONUtils;
import net.zhenghao.zh.generator.entity.GeneratorParamEntity;
import net.zhenghao.zh.generator.entity.TableEntity;
import net.zhenghao.zh.generator.service.ToolGeneratorService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

/**
 * 🙃
 * 🙃 代码生成器
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/04/17 22:07
 * ToolGeneratorController.java
 */
@Controller
@RequestMapping("${zh-admin.api.prefix}/tool/generator")
public class ToolGeneratorController extends AbstractController {

    @Autowired
    private ToolGeneratorService toolGeneratorService;

    /**
     * 数据库列表
     * @param params
     * @return
     */
    @GetMapping("")
    @ResponseBody
    public Page<TableEntity> listTable(@RequestParam Map<String, Object> params){
        return toolGeneratorService.listTable(params);
    }

    /**
     * 生成代码
     * @param params
     * @param response
     * @throws IOException
     */
    @GetMapping("/code")
    @SysLog("生成代码")
    public void generator(@Valid GeneratorParamEntity params, BindingResult results, HttpServletResponse response) throws IOException {
        if (results.hasErrors()) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().write(JSONUtils.objToString(Result.ofFail(results.getFieldError().getDefaultMessage())));
            return;
        }
        byte[] code = toolGeneratorService.generator(params);
        String attachment = "attachment; filename=" + params.getTableName() + ".zip";
        response.reset();
        response.setHeader("Content-Disposition", attachment);
        response.addHeader("Content-Length", "" + code.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(code, response.getOutputStream());
    }
}
