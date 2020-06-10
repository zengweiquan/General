package com.sshy.general.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import org.apache.ibatis.annotations.ConstructorArgs;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayActivePlatform extends Model<PayActivePlatform> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 微信平台
     */
    private Integer wechat;

    /**
     * 支付宝平台
     */
    private Integer alipay;

    /**
     * 银联平台
     */
    private Integer wap;

    private Integer level;

}
