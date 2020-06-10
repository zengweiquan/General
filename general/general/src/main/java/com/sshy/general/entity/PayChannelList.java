package com.sshy.general.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 渠道列表
 * </p>
 *
 * @author 曾伟权
 * @since 2020-06-04
 */
public class PayChannelList extends Model<PayChannelList> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 联系人
     */
    private String contacts;

    /**
     * 联系电话
     */
    private String tel;

    /**
     * 金额
     */
    private Float money;

    /**
     * 下载地址
     */
    private String downUrl;

    /**
     * 包名
     */
    private String productName;

    /**
     * apk地址
     */
    private String apkUrl;

    /**
     * ipa地址
     */
    private String ipa;

    /**
     * 状态 1为正常
     */
    private Integer state;

    private Integer createTime;

    private Integer updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public String getIpa() {
        return ipa;
    }

    public void setIpa(String ipa) {
        this.ipa = ipa;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
        return "PayChannelList{" +
        "id=" + id +
        ", name=" + name +
        ", contacts=" + contacts +
        ", tel=" + tel +
        ", money=" + money +
        ", downUrl=" + downUrl +
        ", productName=" + productName +
        ", apkUrl=" + apkUrl +
        ", ipa=" + ipa +
        ", state=" + state +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
