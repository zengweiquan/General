package com.sshy.general.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sshy.general.annotaction.Sign;
import com.sshy.general.entity.PayBank;
import com.sshy.general.exception.GeneralException;
import com.sshy.general.exception.GlobalExceptionHandle;
import com.sshy.general.mapper.PayBankMapper;
import com.sshy.general.service.PayBankService;
import com.sshy.general.utils.BaseController;
import com.sshy.general.vo.ResultObject;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 曾伟权
 * @since 2020-06-04
 */
@RestController
@RequestMapping("/paybank")
public class PayBankController extends BaseController {
    @Autowired
    private PayBankService payBankService;

    /**
     * 添加银行卡
     * @param card_code     银行卡号码
     * @param card_name     用户名
     * @param bank_name     银行名称
     * @param bank_zi       支行名称
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @SneakyThrows
    @RequestMapping(value = "/insertbankcard",method = RequestMethod.GET)
   // public ResultObject insertBankCard(String card_code,String card_name,String bank_name,String bank_zi){
    public ResultObject insertBankCard(PayBank payBank){

        System.err.println(payBank);
        Integer uid = Objects.requireNonNull(payBank.getUid(), "用户不存在");
        String card_code = requireNonNull(payBank.getCardCode(), "银行卡不能为空");
        String card_name = requireNonNull(payBank.getCardName(),"银行卡用户名不能为空");
        String bank_name = requireNonNull(payBank.getBankName(),"银行名称不能为空");
        String bank_zi = requireNonNull(payBank.getBankZi(),"支行名称不能为空");

        boolean payBanks = payBankService.insertBankCard(uid,card_code,card_name,bank_name,bank_zi);

        if(!payBanks) throw new GeneralException("绑定银行卡失败,请稍后重试");
        return new ResultObject(1,"绑定银行卡成功");
    }

    /**
     * 读取用户卡信息
     * @param uid     用户id
     * @return
     */
    @SneakyThrows
    @RequestMapping("/querybankinfo")
    public ResultObject queryBankInfo(Integer uid,String sign){
        if(Objects.isNull(uid)) throw new GeneralException("用户不存在!");
        PayBank userPayBank = payBankService.queryBankInfo(uid);

        userPayBank = Objects.requireNonNull(userPayBank, "您还没有添加银行卡");

        return new ResultObject(userPayBank);

    }

}

