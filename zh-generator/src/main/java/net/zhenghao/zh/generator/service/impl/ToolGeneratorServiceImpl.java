package net.zhenghao.zh.generator.service.impl;

import com.github.pagehelper.PageHelper;
import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.common.entity.Query;
import net.zhenghao.zh.common.exception.BaseException;
import net.zhenghao.zh.generator.core.GeneratorHandler;
import net.zhenghao.zh.generator.dao.ToolGeneratorMapper;
import net.zhenghao.zh.generator.entity.ColumnEntity;
import net.zhenghao.zh.generator.entity.GeneratorParamEntity;
import net.zhenghao.zh.generator.entity.TableEntity;
import net.zhenghao.zh.generator.service.ToolGeneratorService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 🙃
 * 🙃
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/04/17 22:08
 * ToolGeneratorServiceImpl.java
 */
@Service("toolGeneratorService")
public class ToolGeneratorServiceImpl implements ToolGeneratorService {

    @Autowired
    private ToolGeneratorMapper toolGeneratorMapper;

    @Override
    public Page<TableEntity> listTable(Map<String, Object> params) {
        Query query = new Query(params);
        Page<TableEntity> page = new Page<>(query);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        page.setData(toolGeneratorMapper.listTable(query));
        return page;
    }

    @Override
    public byte[] generator(GeneratorParamEntity params) {
        TableEntity tableEntity = toolGeneratorMapper.getTableByName(params.getTableName());
        List<ColumnEntity> columns = toolGeneratorMapper.listColumn(params.getTableName());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (ZipOutputStream zip = new ZipOutputStream(out)) {
            GeneratorHandler.generatorCode(tableEntity, columns, params, zip);
        } catch (IOException e) {
            throw new BaseException("生成代码失败，表名：" + params.getTableName(), e);
        }
        return out.toByteArray();
    }
}
