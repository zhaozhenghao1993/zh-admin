package com.zhenghao.admin.generator.entity;

import java.io.Serializable;

/**
 * 🙃
 * 🙃 数据表列属性
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/04/20 14:22
 * ColumnEntity.java
 */

public class ColumnEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 列名
     */
    private String columnName;

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 列注释
     */
    private String columnComment;

    /**
     * 属性名，作为类属性名(userId)
     */
    private String fieldName;

    /**
     * 属性名，作为类方法名(UserId)
     */
    private String methodName;

    /**
     * 列数据类型对应java数据类型
     */
    private String fieldType;

    /**
     * 键类型标识
     */
    private String columnKey;

    /**
     * 自增标识auto_increment
     */
    private String extra;

    public ColumnEntity() {
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "ColumnEntity{" +
                "columnName='" + columnName + '\'' +
                ", dataType='" + dataType + '\'' +
                ", columnComment='" + columnComment + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", fieldType='" + fieldType + '\'' +
                ", columnKey='" + columnKey + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }
}
