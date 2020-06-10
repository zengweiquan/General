package com.sshy.general.aop;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.sshy.general.annotaction.Sign;
import com.sshy.general.utils.BaseController;
import com.sshy.general.vo.ResultObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 此切面验证加签
 */
@Component
@Aspect
public class SignAop extends BaseController{

    @Before("@annotation(annotationSign)")
    public void doSign(JoinPoint joinPoint, Sign annotationSign){
        Object target = joinPoint.getTarget();
        boolean isSign = annotationSign.isSign();
        if (!isSign) return;

        System.err.println("切面执行----");
        //获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();                                                              //参数值
        String[] argNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();             // 参数名
        String sign="";
        Map<String,Object> map = new ConcurrentHashMap<>();
        for (int i = 0; i < args.length; i++) {
            if ("sign".equals(argNames[i])){
                sign+=args[i];
            }else {
                map.put(argNames[i],args[i]);
            }
//            System.err.println("切面执行了"+args);
//            System.out.println("第" + (i+1) + "个参数名为:" + argNames[i]+"参数值为:"+args[i]);
        }
        if(!this.checkSign(map, sign)){
            throw new NullPointerException("参数错误");
        }
        System.err.println(map);
        System.err.println("切面结束----");
    }
}
