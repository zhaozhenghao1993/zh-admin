package com.zhenghao.admin.generator.dao;

import com.zhenghao.admin.common.entity.Query;
import com.zhenghao.admin.generator.entity.ColumnEntity;
import com.zhenghao.admin.generator.entity.TableEntity;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 🙃
 * 🙃
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/04/17 22:07
 * ToolGeneratorMapper.java
 */
@MapperScan
@Component
public interface ToolGeneratorMapper {

    List<TableEntity> listTable(Query query);

    TableEntity getTableByName(String tableName);

    List<ColumnEntity> listColumn(String tableName);
}
