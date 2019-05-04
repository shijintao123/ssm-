package com.sjt.crm.interceptor;

import com.sjt.crm.service.UserService;
import com.sjt.crm.utils.AssertUtil;
import com.sjt.crm.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2019/4/18.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private UserService userService;
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        AssertUtil.isNotLogin(userId==null||userService.queryUserById(userId)==null,300,"未登录");
        return true;
    }

}
