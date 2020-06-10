package com.sshy.general.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 下分记录表
 * </p>
 *
 * @author 曾伟权
 * @since 2020-06-04
 */
public class PayScoreRecord extends Model<PayScoreRecord> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 下分者
     */
    private Integer adminId;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 下分数量
     */
    private Integer num;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 下分时间
     */
    private Integer createTime;

    /**
     * 状态；0为待下分，1为完成
     */
    private Integer status;


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

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
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
        return "PayScoreRecord{" +
        "id=" + id +
        ", adminId=" + adminId +
        ", uid=" + uid +
        ", num=" + num +
        ", remarks=" + remarks +
        ", createTime=" + createTime +
        ", status=" + status +
        "}";
    }
}
