package com.sjt.crm.dao;

import com.sjt.crm.base.BaseDao;
import com.sjt.crm.dto.UserDto;
import com.sjt.crm.po.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper extends BaseDao<UserDto>{
    //登录
    public User queryUserByName(String username);
    //修改密码
    public  Integer updateUserById(User user);
    //查询所有客户经理注入select框中
    List<Map> queryCustomerManagers();
    //查找下拉框所有的角色
    List<Map> queryAllRoles();
    //修改用户的状态删除
    Integer updateByIs(Integer id);
}