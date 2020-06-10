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
public class PayAdminBank extends Model<PayAdminBank> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 操作者
     */
    private Integer controllerId;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 银行号码
     */
    private String bankCode;

    /**
     * 开户人
     */
    private String bankAdmin;

    /**
     * 状态，0不可用, 1可用,2为已删除
     */
    private Integer status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getControllerId() {
        return controllerId;
    }

    public void setControllerId(Integer controllerId) {
        this.controllerId = controllerId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankAdmin() {
        return bankAdmin;
    }

    public void setBankAdmin(String bankAdmin) {
        this.bankAdmin = bankAdmin;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "PayAdminBank{" +
        "id=" + id +
        ", controllerId=" + controllerId +
        ", bankName=" + bankName +
        ", bankCode=" + bankCode +
        ", bankAdmin=" + bankAdmin +
        ", status=" + status +
        "}";
    }
}
