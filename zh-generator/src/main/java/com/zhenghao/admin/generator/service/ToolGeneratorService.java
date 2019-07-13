package com.zhenghao.admin.generator.service;

import com.zhenghao.admin.common.entity.Page;
import com.zhenghao.admin.generator.entity.GeneratorParamEntity;
import com.zhenghao.admin.generator.entity.TableEntity;

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

    Page<TableEntity> listTable(Map<String, Object> params);

    byte[] generator(GeneratorParamEntity params);
}
