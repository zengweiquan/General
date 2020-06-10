package com.sshy.general.utils;

import com.sshy.general.exception.GeneralException;

public class ServiceUtils {

    public static void codeVerify(String code) throws GeneralException {
        //5积分小于提现积分  3.在游戏中 4空 6Summary值 不对  8公会不存在
        if ("3".equals(code)) throw new GeneralException("游戏中不能提现");
        if ("5".equals(code)) throw new GeneralException("积分不足");
        if ("8".equals(code)) throw new GeneralException("公会不存在");
        if (!"0".equals(code)) throw new GeneralException("提现失败!");
    }
}
