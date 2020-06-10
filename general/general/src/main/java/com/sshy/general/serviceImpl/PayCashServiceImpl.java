package com.sshy.general.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sshy.general.entity.PayCash;
import com.sshy.general.exception.GeneralException;
import com.sshy.general.mapper.PayCashMapper;
import com.sshy.general.service.PayCashService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sshy.general.utils.BaseController;
import com.sshy.general.utils.MyBean;
import com.sshy.general.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.annotation.Documented;
import java.time.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 曾伟权
 * @since 2020-06-04
 */
@Service
public class PayCashServiceImpl extends ServiceImpl<PayCashMapper, PayCash> implements PayCashService {

    @Resource
    private PayCashMapper payCashMapper;


    /**
     * 提现记录
     * @param uid
     * @param family_id
     * @return
     */
    @Override
    public List<Map<String, Object>> getCashlist(Integer uid, Integer family_id) {
        //当前时间搓
        long endSecond = Instant.now().getEpochSecond();
        //三天前的时间戳从凌晨开始
        int startSecond = BaseController.getTimeSecond(-3);

        //查询最近三天的信息根据提现时间来排序
        List<PayCash> payCash= payCashMapper.selectList(new QueryWrapper<PayCash>()
                .eq("uid", uid).eq("family_id",family_id).
                        between(true,"order_time",startSecond,endSecond).
                        orderBy(true,true,"order_time"));

        List<Map<String,Object>> paylist = new ArrayList<>();

        payCash.stream().forEach(e ->{
            Map<String, Object> map = new ConcurrentHashMap<>();
            map.put("id",e.getId());
            map.put("price",e.getPrice()==null?"":e.getPrice());
            map.put("arrival_account",e.getArrivalAccount()==null?0:e.getArrivalAccount());
            map.put("order_time",e.getOrderTime()==null?"":e.getOrderTime());
            map.put("cash_time",e.getCashTime()==null?"":e.getCashTime());
            map.put("state",e.getState());
            paylist.add(map);
        });
        return paylist;
    }

    /**
     * 提现
     * @param uid
     * @param family_id
     * @param money
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deposit(Integer uid, Integer family_id, Integer money) throws Exception {
        //对积分进行添加操作    msgid 1            //Summary 2添加积分 3减积分                  这里的money是积分
        JSONObject result = BaseController.enable_C_routine(1, uid, family_id,money,  3);

        if(Objects.isNull(result)) throw new GeneralException("参数错误");
        String code = result.getString("Code");                     //5,积分小于提现积分  3,在游戏中 4,空 6,Summary值 不对  8,公会不存在
        //对参数做验证
        ServiceUtils.codeVerify(code);

        String orderId = UUID.randomUUID().toString().replace("-","");                     //订单id
        String charge = "2%";                                                                               //手续费
        int order_time = (int) Instant.now().getEpochSecond();                                              //时间
        int cash_time = (int)Instant.now().getEpochSecond();                                                //打款时间
        int state=1;                                                                                        //打款状态

        return new PayCash().setUid(uid).setFamilyId(family_id).setPrice(Float.valueOf(money)/10)    //这里是money 积分除10
                .setOrderId(orderId).setOrderTime(order_time).setCashTime(cash_time).insert();
    }

    /**
     * PHP用户提现接口
     * @param uid
     * @param family_id
     * @param money
     * @return
     */
    @Override
    public boolean phpDeposit(Integer uid, Integer family_id, Integer money) throws Exception {
        //c程序                                                                     //积分
        JSONObject data = BaseController.enable_C_routine(1, uid, family_id, money* MyBean.rate, 3);
        String userID = data.getString("UserID");     //用户id
        String code = data.getString("Code");         //5积分小于提现积分 4值 6Summary值 不对  8公会不存在
        String lScore = data.getString("lScore");     //积分
        ServiceUtils.codeVerify(code);

        String orderId = UUID.randomUUID().toString().replace("-","");                     //订单id
        String charge = "2%";                                                                               //手续费
        int order_time = (int) Instant.now().getEpochSecond();                                              //时间
        int state=0;                                                                                        //打款状态
        return new PayCash().setUid(uid).setFamilyId(family_id).setPrice(Float.valueOf(money)/10) //金额
                .setOrderId(orderId).setOrderTime(order_time).insert();
    }
}
