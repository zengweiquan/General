package com.sshy.general.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 管理用户
 * </p>
 *
 * @author 曾伟权
 * @since 2020-06-04
 */
public class PayAdmin extends Model<PayAdmin> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父级id
     */
    private Integer pid;

    /**
     * 管理角色 1:代理，2:商务代理，0:会员,9999：超管
     */
    private Integer roleId;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 状态
     */
    private Boolean status;

    /**
     * 最后登录时间
     */
    private Integer lastTime;

    /**
     * 创建时间
     */
    private Integer createTime;

    /**
     * 更新时间
     */
    private Integer updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getLastTime() {
        return lastTime;
    }

    public void setLastTime(Integer lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "PayAdmin{" +
        "id=" + id +
        ", pid=" + pid +
        ", roleId=" + roleId +
        ", username=" + username +
        ", nickName=" + nickName +
        ", password=" + password +
        ", status=" + status +
        ", lastTime=" + lastTime +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
