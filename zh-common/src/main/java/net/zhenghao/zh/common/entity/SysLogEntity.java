package net.zhenghao.zh.common.entity;

import org.springframework.validation.BindingResult;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 系统日志
 * <p>
 * author:zhaozhenghao
 * Email :736720794@qq.com
 * date  :2017年11月20日下午3:22:54
 * SysLogEntity.java
 */
public class SysLogEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    /**
     * 日志id
     */
    private Long id;


    /**
     * 操作用户id
     */
    private Long userId;

    /**
     * 操作用户
     */
    private String username;

    /**
     * 操作
     */
    private String operation;

    /**
     * 方法
     */
    private String method;

    /**
     * 参数
     */
    private String params;

    /**
     * 耗时
     */
    private Long time;

    /**
     * 操作方ip地址
     */
    private String ip;

    /**
     * 操作方浏览器
     */
    private String browser;

    /**
     * 操作方系统
     */
    private String os;

    /**
     * 操作状态 0-成功 1-失败
     */
    private Integer status;

    /**
     * 操作描述
     */
    private String remark;

    /**
     * 日志类型 1-登录 2-访问 3-操作 4-异常 5-授权
     */
    private Integer type;

    /**
     * 用来放 BindingResult
     */
    private BindingResult result;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    public SysLogEntity() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public BindingResult getResult() {
        return result;
    }

    public void setResult(BindingResult result) {
        this.result = result;
    }
}
