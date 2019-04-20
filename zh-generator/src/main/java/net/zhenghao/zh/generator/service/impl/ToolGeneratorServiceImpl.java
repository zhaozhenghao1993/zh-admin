package net.zhenghao.zh.generator.service.impl;

import com.github.pagehelper.PageHelper;
import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.common.entity.Query;
import net.zhenghao.zh.generator.dao.ToolGeneratorMapper;
import net.zhenghao.zh.generator.entity.GeneratorParamEntity;
import net.zhenghao.zh.generator.entity.TableEntity;
import net.zhenghao.zh.generator.service.ToolGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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
        return new byte[0];
    }
}
