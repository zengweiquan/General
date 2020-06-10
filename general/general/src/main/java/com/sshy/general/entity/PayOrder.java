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
public class PayOrder extends Model<PayOrder> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 操作id
     */
    private Integer adminId;

    /**
     * 充值类型，0为线上，1为线下
     */
    private Integer type;

    /**
     * 支付方式: wx, alipay, web(网银), bank(线下汇款)
     */
    private String payType;

    /**
     * 平台编号
     */
    private Integer platformId;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 订单
     */
    private String goodOrder;

    /**
     * 下单时间
     */
    private Integer orderTime;

    /**
     * 支付时间
     */
    private Integer finishTime;

    /**
     * 产品id
     */
    private Integer goodId;

    /**
     * 产品名称
     */
    private String goodName;

    /**
     * 价格，单位元
     */
    private Integer goodPrice;

    /**
     * 状态: 0：已下单，1：未支付, 2：已支付, 3:已完成
     */
    private Integer state;

    /**
     * 平台订单
     */
    private String platformOrder;

    /**
     * 金币数
     */
    private Integer gold;

    /**
     * 调用支付URL
     */
    private String payUrl;

    private Integer bankId;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 0为正常玩家，1为机器人
     */
    private Integer style;

    /**
     * 渠道
     */
    private Integer channel;

    private Integer familyId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getGoodOrder() {
        return goodOrder;
    }

    public void setGoodOrder(String goodOrder) {
        this.goodOrder = goodOrder;
    }

    public Integer getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Integer orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Integer finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Integer getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(Integer goodPrice) {
        this.goodPrice = goodPrice;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getPlatformOrder() {
        return platformOrder;
    }

    public void setPlatformOrder(String platformOrder) {
        this.platformOrder = platformOrder;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getStyle() {
        return style;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "PayOrder{" +
        "id=" + id +
        ", adminId=" + adminId +
        ", type=" + type +
        ", payType=" + payType +
        ", platformId=" + platformId +
        ", uid=" + uid +
        ", goodOrder=" + goodOrder +
        ", orderTime=" + orderTime +
        ", finishTime=" + finishTime +
        ", goodId=" + goodId +
        ", goodName=" + goodName +
        ", goodPrice=" + goodPrice +
        ", state=" + state +
        ", platformOrder=" + platformOrder +
        ", gold=" + gold +
        ", payUrl=" + payUrl +
        ", bankId=" + bankId +
        ", remarks=" + remarks +
        ", style=" + style +
        ", channel=" + channel +
        ", familyId=" + familyId +
        "}";
    }
}
