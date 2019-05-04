package com.sjt.crm.aop;

import com.sjt.crm.annotation.RequestPermission;
import com.sjt.crm.constants.CrmConstant;
import com.sjt.crm.utils.AssertUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Administrator on 2019/4/23.
 */

@Component
@Aspect
public class RequestPermissionAop {
    @Autowired
    private HttpSession httpSession;
    @Pointcut("@annotation(com.sjt.crm.annotation.RequestPermission)")
    public void cut(){}
    @Around("cut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        /**
         * 1.获得当前访问对象的授权码
         * 2.获得当前用户角色的授权码列表
         * 3.判断列表中是否有制定的授权码
         */
        MethodSignature methodSignature= (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        RequestPermission annotation = method.getAnnotation(RequestPermission.class);
        String aclValue = annotation.aclValue();
        List<String> list = (List<String>) httpSession.getAttribute(CrmConstant.USER_PERMISSIONS);
        AssertUtil.isTrue(CollectionUtils.isEmpty(list)||!list.contains(aclValue),"没有权限");
        Object proceed = pjp.proceed();
        return proceed;
    }
}
