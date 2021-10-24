package com.zhenghao.admin.server.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;


/**
 * 数据字典项目
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2021/10/23 22:00
 * SysDictItemEntity.java
 */
public class SysDictItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Long id;

    /**
     * 字典code
     */
    @Length(max = 100, message = "字典编码的长度应该小于100之间")
    private String dictCode;

    /**
     * 字典元素名称
     */
    @Length(min = 1, max = 100, message = "字典项的长度应该在1和100之间")
    @NotBlank(message = "字典项不能为空")
    private String name;

    /**
     * 字典元素值
     */
    @Length(min = 1, max = 100, message = "字典项值的长度应该在1和100之间")
    @NotBlank(message = "字典项值不能为空")
    private String value;

    /**
     * 描述
     */
    @Length(max = 255, message = "描述的长度应该小于255")
    private String description;

    /**
     * 字典元素状态，0：有效，1：无效
     */
    @Range(min = 0, max = 1, message = "字典元素状态不正确")
    @NotNull(message = "字典元素状态不能为空")
    private Integer status;

    /**
     * 排序
     */
    private Integer order;

    /**
     * 创建用户id
     */
    private Long creatorId;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 最后修改人id
     */
    private Long modifierId;

    /**
     * 修改时间
     */
    private Timestamp modifiedTime;


    public SysDictItemEntity() {
        super();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictCode() {
        return dictCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getOrder() {
        return order;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    public Long getModifierId() {
        return modifierId;
    }

    public void setModifiedTime(Timestamp modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Timestamp getModifiedTime() {
        return modifiedTime;
    }

}