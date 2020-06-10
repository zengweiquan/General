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
public class PayPlatform extends Model<PayPlatform> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标识
     */
    private String platformKey;

    /**
     * 平台名
     */
    private String platformName;

    /**
     * 网关
     */
    private String notify;

    /**
     * 等级: 0
     */
    private Integer level;

    /**
     * 支付方式
     */
    private String payStyle;

    /**
     * 是否支持微信支付，1为支持
     */
    private Integer wechat;

    /**
     * 是否支持支付宝1为支持
     */
    private Integer alipay;

    /**
     * 是否支持网银1为支持
     */
    private Integer wap;

    /**
     * 状态， 0未启用， 1启用
     */
    private Integer status;

    /**
     * 备注
     */
    private String remarks;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlatformKey() {
        return platformKey;
    }

    public void setPlatformKey(String platformKey) {
        this.platformKey = platformKey;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getPayStyle() {
        return payStyle;
    }

    public void setPayStyle(String payStyle) {
        this.payStyle = payStyle;
    }

    public Integer getWechat() {
        return wechat;
    }

    public void setWechat(Integer wechat) {
        this.wechat = wechat;
    }

    public Integer getAlipay() {
        return alipay;
    }

    public void setAlipay(Integer alipay) {
        this.alipay = alipay;
    }

    public Integer getWap() {
        return wap;
    }

    public void setWap(Integer wap) {
        this.wap = wap;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "PayPlatform{" +
        "id=" + id +
        ", platformKey=" + platformKey +
        ", platformName=" + platformName +
        ", notify=" + notify +
        ", level=" + level +
        ", payStyle=" + payStyle +
        ", wechat=" + wechat +
        ", alipay=" + alipay +
        ", wap=" + wap +
        ", status=" + status +
        ", remarks=" + remarks +
        "}";
    }
}
