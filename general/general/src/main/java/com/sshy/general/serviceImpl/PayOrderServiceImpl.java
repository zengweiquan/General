package com.sshy.general.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.sshy.general.entity.PayOrder;
import com.sshy.general.exception.GeneralException;
import com.sshy.general.mapper.PayOrderMapper;
import com.sshy.general.service.PayOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sshy.general.utils.BaseController;
import com.sshy.general.utils.MyBean;
import com.sshy.general.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.SocketUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.annotation.Resources;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 曾伟权
 * @since 2020-06-04
 */
@Service
public class PayOrderServiceImpl extends ServiceImpl<PayOrderMapper, PayOrder> implements PayOrderService {

    @Resource
    private PayOrderMapper payOrderMapper;


    /**
     * 创建新订单
     * @param uid
     * @param familyid
     * @param money
     * @return
     */
    @Override
    public String createOrder(int uid, int familyid, int money,Integer OrderType) {
        String str = "p"+ System.currentTimeMillis() + familyid + "" + uid + ""+ money;
        int currentTime = (int)Instant.now().getEpochSecond();
        PayOrder payOrder = new PayOrder();
        payOrder.setUid(uid);
        payOrder.setFamilyId(familyid);
        payOrder.setGoodPrice(money);
        payOrder.setGoodOrder(str);
        payOrder.setType(OrderType);        //支付类型  1线下 0线上
        payOrder.setGold(money * 10);   //统一一毛一分
        payOrder.setOrderTime(currentTime);
        payOrder.setFinishTime(currentTime);
        payOrderMapper.insert(payOrder);
        return str;
    }

    @Override
    public void updateOrderPayUrl(String url, String orderid) {
        QueryWrapper<PayOrder> wrapper = new QueryWrapper<PayOrder>();
        wrapper.eq("good_order", orderid);
        PayOrder obj = payOrderMapper.selectOne(wrapper);
        if(obj == null)
        {
            return;
        }
        obj.setPayUrl(url);
        obj.setState(1);      //未支付
        payOrderMapper.updateById(obj);
    }

    /**
     *  //已支付
     * @param orderid
     * @param payorderid
     */
    @Override
    public void payFinish(String orderid, String payorderid) {
        int createTime = (int)Instant.now().getEpochSecond();
        QueryWrapper<PayOrder> wrapper = new QueryWrapper<PayOrder>();
        wrapper.eq("good_order", orderid);
        PayOrder obj = payOrderMapper.selectOne(wrapper);
        if(obj == null)
        {
            return;
        }
        obj.setOrderTime(createTime);
        obj.setPlatformOrder(payorderid);
        obj.setState(2);  //已支付
        payOrderMapper.updateById(obj);
    }

    /**
     *   已OK
     */
    @Override
    public boolean finishOrderPay(String orderid, String payorderid) {
        int createTime = (int)Instant.now().getEpochSecond();
        QueryWrapper<PayOrder> wrapper = new QueryWrapper<PayOrder>();
        wrapper.eq("good_order", orderid);
        PayOrder obj = payOrderMapper.selectOne(wrapper);
        if(obj == null)
        {
            return false;
        }
        obj.setOrderTime(createTime);
        obj.setPlatformOrder(payorderid);
        obj.setState(3);
        return payOrderMapper.updateById(obj)==1?true:false;
    }
    @Override
    public int getOrderState(String orderid) {
        QueryWrapper<PayOrder> wrapper = new QueryWrapper<PayOrder>();
        wrapper.eq("good_order", orderid);
        PayOrder obj = payOrderMapper.selectOne(wrapper);
        if(obj == null)
        {
            return -1;
        }
        return obj.getState();
    }

    /**
     * 充值记录
     * @param uid
     * @param family_id
     * @return
     */
    @Override
    public  List<Map<String,Object>> get_recharge_record(Integer uid, Integer family_id) {
        //当前时间戳秒
        long endSecond = Instant.now().getEpochSecond();
        //三天前的时间_从凌晨开始
        Integer startSecond = BaseController.getTimeSecond(-3);
        List<PayOrder> payOrders = payOrderMapper.selectList(new QueryWrapper<PayOrder>().eq
                ("uid", uid).eq("family_id", family_id).between("order_time", startSecond, endSecond).
                orderBy(true, true, "order_time"));

        List<Map<String,Object>> list = new CopyOnWriteArrayList<>();;
        payOrders.stream().forEach(e ->{
            ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
            map.put("id",e.getId());
            map.put("order_time",ObjectUtils.isEmpty(e.getOrderTime())==true?"":e.getOrderTime());
            map.put("finish_time",ObjectUtils.isEmpty(e.getFinishTime())==true?"":e.getFinishTime());
            map.put("state",ObjectUtils.isEmpty(e.getState())==true?"":e.getState());
            map.put("good_price",ObjectUtils.isEmpty(e.getGoodPrice())==true?"":e.getGoodPrice());
            map.put("gold",ObjectUtils.isEmpty(e.getGold())==true?"":e.getGold());
            list.add(map);
        });
        return list;
    }

    /**
     * php充值接口
     * @param uid
     * @param family_id
     * @param money
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean recharge(Integer uid, Integer family_id, Integer money) throws Exception {
        //1创建订单
        String orderid = createOrder(uid, family_id, money,1);

        //2.调c程序
        JSONObject data = BaseController.enable_C_routine(1, uid, family_id, money* MyBean.rate, 2);//msgid 1修改积分 Summary 2添加积分 3减积分
        String code = data.getString("Code");         //5积分小于提现积分 4空值  6Summary值不对  8公会不存在
        //code 验证
        ServiceUtils.codeVerify(code);

        //3.支付完成
        String payorderid = UUID.randomUUID().toString().replace("-", "");

        return finishOrderPay(orderid, payorderid);
    }

    /**
     * 根据订单id查询订单详情
     * @param good_order 订单id
     * @return
     */
    @Override
    public PayOrder getOrderdetails(String good_order) {
        return payOrderMapper.selectOne(new QueryWrapper<PayOrder>().eq("good_order",good_order));
    }

}
