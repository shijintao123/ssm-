package com.sjt.crm.base;

import com.sjt.crm.model.ResultInfo;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2019/4/18.
 */
public class BaseController {
    //在运行前运行
    @ModelAttribute
    public void preHandler(HttpServletRequest request){
        request.setAttribute("ctx",request.getContextPath());
    }
    public ResultInfo success(Integer code,String msg,Object result){
        ResultInfo info =new ResultInfo();
        info.setCode(code);
        info.setMsg(msg);
        info.setResult(result);
        return info;
    }
    public ResultInfo success(String msg){
        ResultInfo info =new ResultInfo();
        info.setMsg(msg);
        return info;
    }
    public ResultInfo success(Integer code,String msg){
        ResultInfo info =new ResultInfo();
        info.setCode(code);
        info.setMsg(msg);
        return info;
    }
    public ResultInfo success(String msg,Object result){
        ResultInfo info =new ResultInfo();
        info.setMsg(msg);
        info.setResult(result);
        return info;
    }
}
