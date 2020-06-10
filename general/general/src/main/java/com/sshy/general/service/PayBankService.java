package com.sshy.general.service;

import com.sshy.general.entity.PayBank;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 曾伟权
 * @since 2020-06-04
 */
public interface PayBankService extends IService<PayBank> {

    /**
     * 添加银行卡
     *
     * @param uid
     * @param card_code     银行卡号码
     * @param card_name     用户名
     * @param bank_name     银行名称
     * @param bank_zi       支行名称
     * @return
     */
    boolean insertBankCard(Integer uid, String card_code, String card_name, String bank_name, String bank_zi);

    /**
     * 读取用户卡信息
     * @param uid     用户id
     * @return
     */
    PayBank queryBankInfo(Integer uid);
}
