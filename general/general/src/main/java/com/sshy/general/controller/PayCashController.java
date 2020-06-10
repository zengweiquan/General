package com.sshy.general.controller;
import com.sshy.general.entity.PayCash;
import com.sshy.general.exception.GeneralException;
import com.sshy.general.service.PayCashService;
import com.sshy.general.utils.BaseController;
import com.sshy.general.vo.ResultObject;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import javax.xml.transform.Result;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  提现
 * </p>
 *
 * @author 曾伟权
 * @since 2020-06-04
 */
@RestController
@RequestMapping("/paycash")
public class PayCashController extends BaseController {
    @Autowired
    private PayCashService payCashService;


    /**
     * PHP提现接口
     * @param uid
     * @param family_id
     * @param money
     * @return
     */
    @RequestMapping("/php/deposit")
    public ResultObject phpDeposit (Integer uid,Integer family_id,Integer money) throws Exception {
        uid = Objects.requireNonNull(uid, "用户不存在");
        family_id = Objects.requireNonNull(family_id, "公会id不能为空");
        money = Objects.requireNonNull(money, "请输入提现金额");
        boolean result = payCashService.phpDeposit(uid,family_id,money);
        return result==true?new ResultObject(1,"操作成功"):new ResultObject(1,"操作失败");
    }


    /**
     * 在线提现
     * @param uid
     * @param familyid
     * @param money
     * @return
     */
    @RequestMapping("/deposit")
    public ResultObject deposit(Integer uid,Integer familyid,Integer money) throws Exception {

        uid = Objects.requireNonNull(uid, "用户不存在");

        familyid = Objects.requireNonNull(familyid, "公会id不能为空");

        money = Objects.requireNonNull(money, "请输入提现金额");

        boolean result = payCashService.deposit(uid,familyid,money);

        return result==true?new ResultObject(1,"操作成功"):new ResultObject(1,"操作失败");
    }



    /**
     * 提现记录
     * @param uid
     * @return
     */
    @SneakyThrows
    @RequestMapping("/getcashrecord")
    public ResultObject getCashlist(Integer uid,Integer family_id ){
        uid = Objects.requireNonNull(uid, "用户不存在");
        family_id = Objects.requireNonNull(family_id, "公会id不存在");
        List<Map<String,Object>> userCashLists= payCashService.getCashlist(uid,family_id);
        return new ResultObject(userCashLists);
    }

}

