package com.sshy.general.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sshy.general.annotaction.Sign;
import com.sshy.general.entity.PayOrder;
import com.sshy.general.exception.GeneralException;
import com.sshy.general.service.PayOrderService;
import com.sshy.general.utils.BaseController;
import com.sshy.general.utils.ServiceUtils;
import com.sshy.general.vo.PayResultVO;
import com.sshy.general.vo.ResultObject;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.*;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 曾伟权
 * @since 2020-06-04
 */
@RestController
@RequestMapping("/payorder")
public class PayOrderController extends BaseController {
    //商户token
    public static final String token = "644207206a8d2f50b4bbc97722484f01";
    //商户id
    public static final String shop_id = "27";
    //回调地址
    public static final String tzurl = "http://www.baidu.com";
    //通道id
    public static final String channel = "14";
    //请求接口
    public static final String api = "http://cpay006.91cpay.com/indexH5";

    public static final String searchapi = "http://cpay006.91cpay.com/api/Pay/getOrder";


    @Value("${PayCashServiceImpl.Deposit.Url}")
    private static String url;//="http://192.168.2.195:8650";

    @Autowired
    private PayOrderService payOrderService;

    /**
     * PHP充值接口
     * @param uid
     * @param family_id
     * @param money
     * @return
     */
    @RequestMapping("/php/recharge")
    public ResultObject recharge(Integer uid,Integer family_id,Integer money) throws Exception {
        uid = Objects.requireNonNull(uid, "用户不存在");
        family_id = Objects.requireNonNull(family_id, "公会id不能为空");
        money = Objects.requireNonNull(money, "请输入充值金额");
        boolean result = payOrderService.recharge(uid,family_id,money);
        if(!result) throw new GeneralException("系统异常,充值失败!");
        return new ResultObject(1,"充值成功!");
    }

    /**
     * 充值记录
     * @param uid
     * @param family_id
     * @return
     */
    @GetMapping("/rechargerecord")
    public ResultObject get_recharge_record(Integer uid,Integer family_id,String sign){
        //验签
        HashMap<String, String> signmap = new HashMap<String, String>();
        signmap.put("uid",String.valueOf(uid));
        signmap.put("familyid", String.valueOf(family_id));
        if(!this.checkSign(signmap, sign)){
            return new ResultObject("参数错误");
        }
        uid = Objects.requireNonNull(uid, "账户不能为空");
        family_id = Objects.requireNonNull(family_id,"公会id不能为空");
        List<Map<String, Object>> result = payOrderService.get_recharge_record(uid, family_id);
        return new ResultObject(result);
    }

    /**
     * 支付成功回调
     * @param
     * @return
     */
    @SneakyThrows
    @PostMapping("/payresult")
    public String payresult(PayResultVO obj)
    {
        //验签
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("channel",obj.channel);
        map.put("create_time", obj.create_time);
        map.put("end_time",obj.end_time);
        map.put("mark_buy",obj.mark_buy);
        map.put("mark_sell",obj.mark_sell);
        map.put("money",obj.money);
        map.put("orderid",obj.orderid);
        map.put("pay_money",obj.pay_money);
        map.put("shop_id",obj.shop_id);
        map.put("status",obj.status);
        map.put("trade_no",obj.trade_no);

        String str = MapToURLParam(map);

        str +=  "token=" + token;
        String sign = md5(str);
        if(obj.sign.equals(sign))
        {
            int state = payOrderService.getOrderState(obj.trade_no);
            if(state == 1){

                payOrderService.payFinish(obj.trade_no, obj.orderid);

                PayOrder payOrder = payOrderService.getOrderdetails(obj.trade_no);

                payOrder = Objects.requireNonNull(payOrder, "订单号不合法");
                //调c程序那边
                //对积分进行添加操作    msgid 1            //Summary 2添加积分 3减积分
                JSONObject data = BaseController.enable_C_routine(1, payOrder.getUid(), payOrder.getFamilyId(), payOrder.getGold(), 2);

                String code = data.getString("Code");         //5积分小于提现积分 4空值  6Summary值不对  8公会不存在
                //对code验证
                ServiceUtils.codeVerify(code);
                payOrderService.finishOrderPay(obj.trade_no, obj.orderid);
            }
            return "{\"code\":1,\"msg\":\"ok\"}";
        }else{
            return "";
        }
    }

    /**
     * 查询订单,不对外开放，无需验签
     * @param
     * @return
     */
    @SneakyThrows
    @RequestMapping("/searchorder")
    public ResultObject searchOrder(String orderid)
    {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("trade_no", orderid);
        map.put("shop_id", shop_id);
        String str = MapToURLParam(map);
        str +=  "token=" + token;
        str += "&sign=" + md5(str);
        String result = sendURL(searchapi, str);
        JSONObject resultObj = JSON.parseObject(result);
        if(resultObj.getInteger("status") == 1)
        {
            //已完成支付
            HashMap<String, String> res = new HashMap<String, String>();
            res.put("result", "ok");
            return new ResultObject(res);
        }else{
            //未完成
            return new ResultObject("充值未完成");
        }
    }

    /**
     * 线上发起支付
     * @param
     * @return
     */
    @SneakyThrows
    @RequestMapping("/pay")
    public ResultObject pay(int uid, int familyid, int money, String sign){
        //验签
        HashMap<String, String> signmap = new HashMap<String, String>();
        signmap.put("uid",String.valueOf(uid));
        signmap.put("familyid", String.valueOf(familyid));
        signmap.put("money",String.valueOf(money));
        if(!this.checkSign(signmap, sign)){
            return new ResultObject("参数错误");
        }

        String orderid = payOrderService.createOrder(uid, familyid, money,0);       //0线上充值类型
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("channel",channel);
        map.put("money", String.valueOf(money * 100));
        map.put("shop_id",shop_id);
        map.put("trade_no",orderid);
        map.put("tzurl",UrlEncode(tzurl));

        String str = MapToURLParam(map).toLowerCase();

        str +=  "token=" + token;
        str += "&sign=" + md5(str);
        String result = sendURL(api, str);

        JSONObject resultObj = JSON.parseObject(result);
        if(resultObj.getInteger("status") == 0)
        {
            HashMap<String, String> res = new HashMap<String, String>();
            res.put("url", "http://www.baidu.com");
            return new ResultObject(res);
            //return new ResultObject("充值通道已关闭");
        }else{
            String payUrl = resultObj.getJSONObject("data").getString("url");
            payOrderService.updateOrderPayUrl(payUrl, orderid);
            HashMap<String, String> res = new HashMap<String, String>();
            res.put("url", payUrl);
            return new ResultObject(res);
        }
    }
}

