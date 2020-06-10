package com.sshy.general.vo;

import lombok.Data;

@Data
public class PayResultVO {
    public String channel;
    public String money;
    public String pay_money;
    public String shop_id;
    public String mark_buy;
    public String mark_sell;
    //Order表_订单
    public String trade_no;
    public String orderid;
    public String end_time;
    public String create_time;
    public String status;
    public String sign;
}
