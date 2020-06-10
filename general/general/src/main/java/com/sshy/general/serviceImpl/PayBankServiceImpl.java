package com.sshy.general.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sshy.general.entity.PayBank;
import com.sshy.general.mapper.PayBankMapper;
import com.sshy.general.service.PayBankService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 曾伟权
 * @since 2020-06-04
 */
@Service
public class PayBankServiceImpl extends ServiceImpl<PayBankMapper, PayBank> implements PayBankService {

    @Resource
    private PayBankMapper payBankMapper;

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
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertBankCard(Integer uid, String card_code, String card_name, String bank_name, String bank_zi) {
        Integer userBank = payBankMapper.selectCount(new QueryWrapper<PayBank>().eq("uid", uid));
        if (userBank==0){
            return new PayBank().setUid(uid).setCardCode(card_code).setCardName(card_name)
                    .setBankName(bank_name).setBankZi(bank_zi).insert();
        }else{
            return new PayBank().setCardCode(card_code).setCardName(card_name)
                    .setBankName(bank_name).setBankZi(bank_zi).update(new QueryWrapper<PayBank>().eq("uid", uid));
        }




    }
    /**
     * 读取用户卡信息
     * @param uid     用户id
     * @return
     */
    @Override
    public PayBank queryBankInfo(Integer uid) {
        PayBank userPayBank = payBankMapper.selectOne(new QueryWrapper<PayBank>().eq("uid", uid));
        return userPayBank;
    }
}
