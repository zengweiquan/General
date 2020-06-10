package com.sshy.general.service;

import com.sshy.general.entity.PayOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
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
public interface PayOrderService extends IService<PayOrder> {

    /**
     * 创建订单
     * @param uid
     * @param familyid
     * @param money
     * @return
     */
    String createOrder(int uid, int familyid, int money,Integer OrderType);

    /**
     * 更新订单支付路径
     * @param url
     * @param orderid
     */
    void updateOrderPayUrl(String url, String orderid);
    /**
     *支付成功
     * @param orderid
     * @param payorderid
     */
    void payFinish(String orderid, String payorderid);
    /**
     * 完成订单
     * @param orderid
     * @param payorderid
     */
    boolean finishOrderPay(String orderid, String payorderid);
    /**
     * 获取订单状态
     * @param orderid
     * @return
     */
    int getOrderState(String orderid);

    /**
     * 充值记录
     * @param uid
     * @param family_id
     * @return
     */
    List<Map<String,Object>> get_recharge_record(Integer uid, Integer family_id);

    /**
     * php充值接口
     * @param uid
     * @param family_id
     * @param money
     * @return
     */
    boolean recharge(Integer uid, Integer family_id, Integer money) throws Exception;

    /**
     * 根据订单id查询订单详情
     * @param trade_no 订单id
     * @return
     */
    PayOrder getOrderdetails(String trade_no);
}
