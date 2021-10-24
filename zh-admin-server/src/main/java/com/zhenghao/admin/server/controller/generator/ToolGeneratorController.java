package com.zhenghao.admin.server.controller.generator;

import com.zhenghao.admin.common.entity.Page;
import com.zhenghao.admin.common.entity.Result;
import com.zhenghao.admin.server.annotation.SysLog;
import com.zhenghao.admin.server.controller.AbstractController;
import com.zhenghao.admin.server.entity.GeneratorParamEntity;
import com.zhenghao.admin.server.entity.TableEntity;
import com.zhenghao.admin.server.service.ToolGeneratorService;
import com.zhenghao.admin.server.util.ResponseUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

import static com.zhenghao.admin.common.constant.SystemConstants.API_PREFIX;

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
@RequestMapping(API_PREFIX + "/tool/generator")
public class ToolGeneratorController extends AbstractController {

    private final ToolGeneratorService toolGeneratorService;

    @Autowired
    public ToolGeneratorController(ToolGeneratorService toolGeneratorService) {
        this.toolGeneratorService = toolGeneratorService;
    }

    /**
     * 数据库列表
     *
     * @param params
     * @return
     */
    @GetMapping("")
    @ResponseBody
    public Result<Page<TableEntity>> listTable(@RequestParam Map<String, Object> params) {
        return toolGeneratorService.listTable(params);
    }

    /**
     * 生成代码
     *
     * @param params
     * @param response
     * @throws IOException
     */
    @GetMapping("/code")
    @SysLog("生成代码")
    public void generator(@Valid GeneratorParamEntity params, BindingResult results, HttpServletResponse response) throws IOException {
        if (results.hasErrors()) {
            ResponseUtils.setResultResponse(response, Result.ofFail(results.getFieldError().getDefaultMessage()));
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
