package com.sshy.general.service;

import com.sshy.general.entity.PayCash;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sshy.general.exception.GeneralException;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 曾伟权
 * @since 2020-06-04
 */
public interface PayCashService extends IService<PayCash> {

    /**
     * 根据uid 查询提现记录
     *
     * @param familyid
     * @param uid
     * @return
     */
    List<Map<String, Object>> getCashlist(Integer familyid, Integer uid);

    /**
     * 提现
     * @param uid
     * @param familyid
     * @param money
     * @return
     */
    boolean deposit(Integer uid, Integer familyid, Integer money) throws Exception;

    /**
     * PhP后台提现接口
     * @param uid
     * @param familyid
     * @param money
     * @return
     */
    boolean phpDeposit(Integer uid, Integer familyid, Integer money) throws GeneralException, Exception;
}
