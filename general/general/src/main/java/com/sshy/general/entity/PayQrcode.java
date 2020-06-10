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
public class PayQrcode extends Model<PayQrcode> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 1：手机银行, 3：支付宝，4：微信
     */
    private Integer payType;

    /**
     * 描述
     */
    private String desc;

    /**
     * 名称
     */
    private String title;

    /**
     * 二维码地址(上传)
     */
    private String url;

    /**
     * 等级
     */
    private Integer level;

    /**
     * 状态，0不可用，1可用
     */
    private Integer status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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
        return "PayQrcode{" +
        "id=" + id +
        ", payType=" + payType +
        ", desc=" + desc +
        ", title=" + title +
        ", url=" + url +
        ", level=" + level +
        ", status=" + status +
        "}";
    }
}
