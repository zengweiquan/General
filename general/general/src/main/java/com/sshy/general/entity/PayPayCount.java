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
public class PayPayCount extends Model<PayPayCount> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 下单次数
     */
    private Integer orderCount;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 成功冲值次数
     */
    private Integer payNumber;

    /**
     * 充值总额
     */
    private Integer payCount;

    /**
     * 提现次数
     */
    private Integer cashNumber;

    /**
     * 提现总额
     */
    private Integer cashCount;

    /**
     * 等级,默认为0
     */
    private Integer level;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(Integer payNumber) {
        this.payNumber = payNumber;
    }

    public Integer getPayCount() {
        return payCount;
    }

    public void setPayCount(Integer payCount) {
        this.payCount = payCount;
    }

    public Integer getCashNumber() {
        return cashNumber;
    }

    public void setCashNumber(Integer cashNumber) {
        this.cashNumber = cashNumber;
    }

    public Integer getCashCount() {
        return cashCount;
    }

    public void setCashCount(Integer cashCount) {
        this.cashCount = cashCount;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "PayPayCount{" +
        "id=" + id +
        ", orderCount=" + orderCount +
        ", uid=" + uid +
        ", payNumber=" + payNumber +
        ", payCount=" + payCount +
        ", cashNumber=" + cashNumber +
        ", cashCount=" + cashCount +
        ", level=" + level +
        "}";
    }
}
