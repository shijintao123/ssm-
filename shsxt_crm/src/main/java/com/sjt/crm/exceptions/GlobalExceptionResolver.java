package com.sjt.crm.exceptions;

import com.alibaba.fastjson.JSON;
import com.sjt.crm.model.ResultInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2019/4/16.
 */
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver{
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object target,
                                         Exception ex) {
        //返回视图
        ModelAndView mv=createModeAndView(request,ex);
        if(ex instanceof  LoginException){
            LoginException e= (LoginException) ex;
            mv.addObject("errorMsg",e.getMsg());
            mv.setViewName("login_error");
            return mv;
        }
        HandlerMethod handlerMethod= (HandlerMethod) target;
        Method method = handlerMethod.getMethod();
        ResponseBody annotation = method.getAnnotation(ResponseBody.class);//获取@ResoinseBody注解
        if(annotation==null){
            //注解为空代表是非json请求
            if(ex instanceof ParamsException){
                ParamsException e = (ParamsException) ex;
                mv.addObject("errorMsg",e.getMsg());//默认错误信息
            }
        }else{
            //如果是json对象 默认数据
            ResultInfo info=new ResultInfo();
            info.setCode(300);
            info.setMsg("系统繁忙");
            if(ex instanceof  ParamsException){
                //如果属于自定义参数异常对象
                ParamsException e = (ParamsException) ex;
                info.setCode(e.getCode());
                info.setMsg(e.getMsg());
            }
            //返回json对象，以流的形式输出
            PrintWriter writer = null;
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=utf-8");
            try {
             writer=response.getWriter();
             writer.write(JSON.toJSONString(info));
             writer.flush();
             writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(writer!=null){
                    writer.close();
                }
            }
        }
        return mv;
    }

    private ModelAndView createModeAndView(HttpServletRequest request, Exception ex) {
        ModelAndView mv=new ModelAndView();
        mv.setViewName("error");//设置错误页面
        mv.addObject("errorMsg",ex.getMessage());//默认错误信息
        mv.addObject("ctx",request.getContextPath());//传递当前路径
        mv.addObject("uri",request.getRequestURI());//传递当前访问的控制器
        return mv;
    }
}
