package com.zhenghao.admin.server.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Timestamp;


/**
 * 
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2021/10/23 21:00
 * SysDictEntity.java
 */
public class SysDictEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private Long id;
	
	/**
	 * 字典名称
	 */
	@Length(min = 1, max = 100, message = "字典名称的长度应该在1和100之间")
	@NotBlank(message = "字典名称不能为空")
	private String name;
	
	/**
	 * 字典编码
	 */
	@Length(min = 1, max = 100, message = "字典编码的长度应该在1和100之间")
	@NotBlank(message = "字典编码不能为空")
	private String code;
	
	/**
	 * 描述
	 */
	@Length(max = 255, message = "描述的长度应该小于255")
	private String description;
	
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
	
	
	public SysDictEntity() {
		super();
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
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