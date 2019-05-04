package com.sjt.crm.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.sjt.crm.base.BaseController;
import com.sjt.crm.constants.CrmConstant;
import com.sjt.crm.dao.PermissionMapper;
import com.sjt.crm.dto.UserDto;
import com.sjt.crm.service.PermissionService;
import com.sjt.crm.service.UserService;
import com.sjt.crm.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2019/4/15.
 */
@Controller
public class IndexController extends BaseController{
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private UserService userService;
     @RequestMapping("index")
    public String index(){
        return "index";
    }
    @RequestMapping("main")
    public String main01(HttpServletRequest request){
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        List<String> strings = permissionService.queryPermissionByUserId(userId);
        request.getSession().setAttribute(CrmConstant.USER_PERMISSIONS,strings);
        //查找用户名以及权限
        UserDto userDto = userService.queryById(userId);
        request.setAttribute("user",userDto);
        return "main";
    }
}
