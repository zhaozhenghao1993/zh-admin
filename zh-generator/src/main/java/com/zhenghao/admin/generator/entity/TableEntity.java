package com.zhenghao.admin.generator.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 🙃
 * 🙃 数据表属性
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/04/20 14:16
 * TableEntity.java
 */

public class TableEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 数据库引擎类型
     */
    private String engine;

    /**
     * 表备注
     */
    private String tableComment;

    /**
     * 主键
     */
    private ColumnEntity pk;

    /**
     * 表 列详情
     */
    private List<ColumnEntity> columns;

    /**
     * 类名，作为实例对象使用（sysUser）
     */
    private String objName;

    /**
     * 类名，作为类型使用（SysUser）
     */
    private String className;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    public TableEntity() {
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public ColumnEntity getPk() {
        return pk;
    }

    public void setPk(ColumnEntity pk) {
        this.pk = pk;
    }

    public List<ColumnEntity> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnEntity> columns) {
        this.columns = columns;
    }

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TableEntity{" +
                "tableName='" + tableName + '\'' +
                ", engine='" + engine + '\'' +
                ", tableComment='" + tableComment + '\'' +
                ", pk=" + pk +
                ", columns=" + columns +
                ", objName='" + objName + '\'' +
                ", className='" + className + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
