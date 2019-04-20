package net.zhenghao.zh.generator.service;

import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.generator.entity.GeneratorParamEntity;
import net.zhenghao.zh.generator.entity.TableEntity;

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
