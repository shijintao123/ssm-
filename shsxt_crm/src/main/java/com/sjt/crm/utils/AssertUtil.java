package com.sjt.crm.utils;

import com.sjt.crm.exceptions.LoginException;
import com.sjt.crm.exceptions.ParamsException;

/**
 * Created by Administrator on 2019/4/16.
 */
public class AssertUtil {
    public static void isTrue(boolean flag,Integer code,String msg){
        if(flag){
            throw  new ParamsException(code,msg);
        }
    }
    public static void isTrue(boolean flag,String msg){
        if(flag){
            throw  new ParamsException(msg);
        }
    }
    public static void isNotLogin(boolean flag,Integer code,String msg){
        if(flag){
            throw  new LoginException(msg);
        }
    }
}
