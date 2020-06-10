package com.sshy.general.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 曾伟权
 * @since 2020-06-04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class PayCash extends Model<PayCash> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 操作者
     */
    private Integer adminId;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 实际到帐
     */
    private Float arrivalAccount;

    /**
     * 手续费
     */
    private String charge;

    /**
     * 金额
     */
    private Float price;

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 时间
     */
    private Integer orderTime;

    /**
     * 打款时间
     */
    private Integer cashTime;

    /**
     * 提现平台
     */
    private Integer platformId;

    /**
     * 状态，0：未打款， 1:已完成,2锁定,3取消，4拒绝
     */
    private Integer state;

    /**
     * 备注信息
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

}
