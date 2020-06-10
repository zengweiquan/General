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
public class PayBank extends Model<PayBank> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 卡号
     */
    private String cardCode;

    /**
     * 卡号名称
     */
    private String cardName;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 开户地址
     */
    private String bankAddress;

    /**
     * 支行名称
     */
    private String bankZi;

    /**
     * 更新或插入时间
     */
    private Integer updateTime;

    /**
     * 公会id
     */
    private Integer familyId;

}
