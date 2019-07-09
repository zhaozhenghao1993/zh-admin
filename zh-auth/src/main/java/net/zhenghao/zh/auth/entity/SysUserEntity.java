package net.zhenghao.zh.auth.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 系统用户
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年11月22日 下午3:16:19
 * SysUserEntity.java
 */
public class SysUserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long id;

    /**
     * 组织id
     */
    private Long orgId;

    /**
     * 用户名
     */
    @Length(min = 5, max = 20, message = "username的长度应该在5和20之间")
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    @Length(min = 5, max = 20, message = "name的长度应该在5和20之间")
    private String name;

    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 手机号
     */
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$", message = "手机号格式不正确")
    private String mobile;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 状态(0:正常   1:禁用)
     */
    private Integer status;

    /**
     * 个人主题，dark（默认）,light
     */
    @Pattern(regexp = "^(dark|light)$", message = "个人主题格式不正确")
    private String theme;

    /**
     * 主题色: 1  薄暮  ,2  火山  ,3  日暮  ,4  明青  ,5  极光绿  ,6  拂晓蓝（默认）,7  极客蓝 ,8 酱紫
     */
    @Range(min = 1, max = 8, message = "主题色值不正确")
    private Integer color;
    /**
     * 创建人id
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
     * 最后修改时间
     */
    private Timestamp modifiedTime;

    /**
     * 角色id列表
     */
    private List<Long> roleIdList;

    /**
     * 角色名称列表
     */
    private List<SysRoleEntity> roles;

    /**
     * 权限列表
     */
    private List<SysMenuEntity> perms;

    /**
     * 岗位id列表
     */
    private List<Long> postIdList;

    /**
     * 岗位列表
     */
    private List<SysPostEntity> posts;

    /**
     * 组织列表，按照组织级别排序，取当前组织就获取列表最后一个
     */
    private List<SysOrgEntity> orgs;

    public SysUserEntity() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getModifierId() {
        return modifierId;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    public Timestamp getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Timestamp modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public List<SysRoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRoleEntity> roles) {
        this.roles = roles;
    }

    public List<SysMenuEntity> getPerms() {
        return perms;
    }

    public void setPerms(List<SysMenuEntity> perms) {
        this.perms = perms;
    }

    public List<Long> getPostIdList() {
        return postIdList;
    }

    public void setPostIdList(List<Long> postIdList) {
        this.postIdList = postIdList;
    }

    public List<SysPostEntity> getPosts() {
        return posts;
    }

    public void setPosts(List<SysPostEntity> posts) {
        this.posts = posts;
    }

    public List<SysOrgEntity> getOrgs() {
        return orgs;
    }

    public void setOrgs(List<SysOrgEntity> orgs) {
        this.orgs = orgs;
    }
}
