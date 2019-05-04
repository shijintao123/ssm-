package com.sjt.crm.controller;

import com.sjt.crm.base.BaseController;
import com.sjt.crm.constants.CrmConstant;
import com.sjt.crm.dto.UserDto;
import com.sjt.crm.model.ResultInfo;
import com.sjt.crm.model.UserInfo;
import com.sjt.crm.po.UserRole;
import com.sjt.crm.query.SaleChanceQuery;
import com.sjt.crm.query.UserQuery;
import com.sjt.crm.service.UserRoleService;
import com.sjt.crm.service.UserService;
import com.sjt.crm.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/16.
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;


    @RequestMapping("index")
    public String index(){
        return "user";
    }
    @RequestMapping("login")
    @ResponseBody
    public ResultInfo login(String userName,String password){
        UserInfo info = userService.login(userName, password);
        return success(200,"登录成功",info);
    }
    @RequestMapping("updatePassword")
    @ResponseBody
    public ResultInfo updatePwd(String oldPassword,
                                String newPassword,
                                String confirmPassword,
                                HttpServletRequest request){
        //调用工具类获取session对象
        int userId = LoginUserUtil.releaseUserIdFromCookie(request);
        userService.updatePwd(oldPassword,newPassword,confirmPassword,userId);
        return success(200,"修改成功");
    }
    @RequestMapping("queryCustomerManagers")
    @ResponseBody
    public List<Map> queryCustomerManagers(){
        return userService.queryCustomerManagers();
    }

    @RequestMapping("queryUserByParams")
    @ResponseBody
    public Map<String, Object> queryUserByParams(UserQuery userQuery,
                                                     @RequestParam(defaultValue = "1")Integer page,
                                                     @RequestParam(defaultValue = "10")Integer rows){
        userQuery.setPageNum(page);
        userQuery.setPageSize(rows);
        return userService.queryForPageUser(userQuery);
    }
    @RequestMapping("queryAllRoles")
    @ResponseBody
    public List<Map> queryAllRoles(){
        return userService.queryAllRoles();
    }
    @RequestMapping("saveOrUpdateUser")
    @ResponseBody
    public ResultInfo saveOrUpdateUser(UserDto userDto,Integer[] roleIds){
        userService.saveOrUpdateUser(userDto, roleIds);
        return success("操作成功");
    }
    //deleteUser
    @RequestMapping("deleteUser")
    @ResponseBody
    public ResultInfo deleteUser(Integer[] ids){
        userService.deleteUser(ids);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }
}
