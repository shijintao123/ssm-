package com.sjt.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sjt.crm.base.BaseQuery;
import com.sjt.crm.base.BaseService;
import com.sjt.crm.constants.CrmConstant;
import com.sjt.crm.dao.UserMapper;
import com.sjt.crm.dao.UserRoleMapper;
import com.sjt.crm.dto.UserDto;
import com.sjt.crm.model.ResultInfo;
import com.sjt.crm.model.UserInfo;
import com.sjt.crm.po.User;
import com.sjt.crm.po.UserRole;
import com.sjt.crm.query.UserQuery;
import com.sjt.crm.utils.AssertUtil;
import com.sjt.crm.utils.Md5Util;
import com.sjt.crm.utils.UserIDBase64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;

import java.util.*;

/**
 * Created by Administrator on 2019/4/16.
 */
@Service
public class UserService extends BaseService<UserDto>{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private  UserRoleMapper userRoleMapper;

    /**
     * 登录
     * @param userName
     * @param password
     */
    public UserInfo login(String userName, String password){
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名为空");
        AssertUtil.isTrue(StringUtils.isBlank(password),"密码为空");
        User user = userMapper.queryUserByName(userName);
        AssertUtil.isTrue(user==null,"用户名或者密码错误");
        AssertUtil.isTrue(!Md5Util.encode(password).equals(user.getUserPwd()),"用户名或者密码错误");
        UserInfo info =new UserInfo();
        //加密
        info.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
        return info;
    }

    /**
     * 修改密码
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     * @param userId
     */
    public void updatePwd(String oldPassword,
                          String newPassword,
                          String confirmPassword,
                          Integer userId){
        checkPassword(oldPassword,newPassword,confirmPassword);
        //判断旧密码是否为原来的密码
        User user = userMapper.queryById(userId);
        AssertUtil.isTrue(user==null,"该用户不存在或者被注销");
        AssertUtil.isTrue(!Md5Util.encode(oldPassword).equals(user.getUserPwd()),"旧密码错误");
        user.setUserPwd(Md5Util.encode(newPassword));
        Integer count = userMapper.updateUserById(user);
        AssertUtil.isTrue(count<0,"更新密码失败");
    }

    /**
     * 判断密码非空
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     */
    private void checkPassword(String oldPassword, String newPassword, String confirmPassword) {
        AssertUtil.isTrue(StringUtils.isBlank(oldPassword),"旧密码为空");
        AssertUtil.isTrue(StringUtils.isBlank(newPassword),"新密码为空");
        AssertUtil.isTrue(!newPassword.equals(confirmPassword),"两次密码不一致");
    }
    public User queryUserById(Integer id){
        return userMapper.queryById(id);
    }
    public List<Map> queryCustomerManagers(){
        return userMapper.queryCustomerManagers();
    }
    public List<Map> queryAllRoles(){
        return userMapper.queryAllRoles();
    }
    public void saveOrUpdateUser(UserDto user,Integer[] roleIds){
        /*
        1.校验参数是否为空
        2.用户名唯一
         */
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserName()),"用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(user.getTrueName()),"真实姓名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(user.getEmail()),"邮箱不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(user.getPhone()),"电话号码不能为空");
        user.setUpdateDate(new Date());
        if(user.getId()==null){
            AssertUtil.isTrue(userMapper.queryUserByName(user.getUserName())!=null,"用户名已存在");
            //代表是添加
            user.setCreateDate(new Date());
            user.setIsValid(1);
            user.setUserPwd(Md5Util.encode("123456"));
            AssertUtil.isTrue(userMapper.save(user)<1,"操作失败");
        }else{
            /*
                    1.修改用户名
                        如果是自己本身的用户名，则不做用户名校验
                        如果修改了用户名，则要进行用户名唯一性进行校验
                    2.更新数据,先更新用户的数据，如果存在权限中间表中存在这数据，则需要
             */
            if(!userMapper.queryById(user.getId()).getUserName().equals(user.getUserName())){
                AssertUtil.isTrue(userMapper.queryUserByName(user.getUserName())!=null,"用户名已存在");
            }
            AssertUtil.isTrue(userMapper.update(user)<1,CrmConstant.OPS_FAILED_MSG);
            Integer total=userRoleMapper.queryCountByUserId(user.getId());
            if(total>0){
                //需要批量删除
                AssertUtil.isTrue(userRoleMapper.deleteByUserId(user.getId())<total,CrmConstant.OPS_FAILED_MSG);
            }
        }
        //进行批量添加
        if(roleIds!=null&&roleIds.length>0){
            Integer userId=user.getId();
            List<UserRole> list=new ArrayList<>();
            for(Integer roleId: roleIds){
                UserRole role=new UserRole();
                role.setUserId(userId);
                role.setCreateDate(new Date());
                role.setUpdateDate(new Date());
                role.setRoleId(roleId);
                list.add(role);
            }
            AssertUtil.isTrue(userRoleMapper.saveBatch(list)<list.size(),"操作失败");
        }
    }
    public Map<String,Object> queryForPageUser(UserQuery baseQuery) throws DataAccessException {
        PageHelper.startPage(baseQuery.getPageNum(),baseQuery.getPageSize());
        List<UserDto> entities = userMapper.queryByParams(baseQuery);
        PageInfo<UserDto> pageInfo=new PageInfo<UserDto>(entities);
        Map<String,Object> map=new HashMap<String,Object>();
        for(UserDto userDto:entities){
            if(null!=userDto.getRoleIdStr()){
                //分割字符串
                String[] split = userDto.getRoleIdStr().split(",");
                List<Integer> list =new ArrayList<>();
                for (String roleId:split){
                    list.add(Integer.valueOf(roleId));
                }
                userDto.setRoleIds(list);
            }else{
                userDto.setRoleIds(new ArrayList<Integer>());
            }
        }
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    public void deleteUser(Integer[] ids){
        for(Integer id:ids){
            //先删除用户
            AssertUtil.isTrue(userMapper.updateByIs(id)<1,CrmConstant.OPS_FAILED_MSG);
            //判断该角色是否存在权限
            Integer count = userRoleMapper.queryCountByUserId(id);
            if(count>0){
                //再删除用户的角色权限
                AssertUtil.isTrue(userRoleMapper.deleteByUserId(id)<count,CrmConstant.OPS_FAILED_MSG);
            }
        }
    }
}
