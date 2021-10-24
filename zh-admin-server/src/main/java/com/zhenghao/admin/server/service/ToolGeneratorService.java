package com.zhenghao.admin.server.service;

import com.zhenghao.admin.common.entity.Page;
import com.zhenghao.admin.common.entity.Result;
import com.zhenghao.admin.server.entity.GeneratorParamEntity;
import com.zhenghao.admin.server.entity.TableEntity;

import java.util.Map;

/**
 * 🙃
 * 🙃
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/04/17 22:08
 * ToolGeneratorService.java
 */

public interface ToolGeneratorService {

    Result<Page<TableEntity>> listTable(Map<String, Object> params);

    byte[] generator(GeneratorParamEntity params);
}
