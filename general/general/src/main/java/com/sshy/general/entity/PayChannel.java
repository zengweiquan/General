package com.sshy.general.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 曾伟权
 * @since 2020-06-04
 */
public class PayChannel extends Model<PayChannel> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 数据库地址
     */
    private String dbHost;

    /**
     * 端口
     */
    private String dbPort;

    /**
     * 用户名
     */
    private String dbUser;

    /**
     * 密码
     */
    private String dbPwd;

    /**
     * 表名
     */
    private String dbName;

    private String dbLogsName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDbHost() {
        return dbHost;
    }

    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }

    public String getDbPort() {
        return dbPort;
    }

    public void setDbPort(String dbPort) {
        this.dbPort = dbPort;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPwd() {
        return dbPwd;
    }

    public void setDbPwd(String dbPwd) {
        this.dbPwd = dbPwd;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbLogsName() {
        return dbLogsName;
    }

    public void setDbLogsName(String dbLogsName) {
        this.dbLogsName = dbLogsName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "PayChannel{" +
        "id=" + id +
        ", dbHost=" + dbHost +
        ", dbPort=" + dbPort +
        ", dbUser=" + dbUser +
        ", dbPwd=" + dbPwd +
        ", dbName=" + dbName +
        ", dbLogsName=" + dbLogsName +
        "}";
    }
}
