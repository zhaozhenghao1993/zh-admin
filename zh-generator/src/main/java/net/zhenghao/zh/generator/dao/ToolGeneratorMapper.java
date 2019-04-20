package net.zhenghao.zh.generator.dao;

import net.zhenghao.zh.common.entity.Query;
import net.zhenghao.zh.generator.entity.ColumnEntity;
import net.zhenghao.zh.generator.entity.TableEntity;
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
