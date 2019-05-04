package com.sjt.crm.service;

import com.sjt.crm.base.BaseService;
import com.sjt.crm.constants.CrmConstant;
import com.sjt.crm.dao.CustomerServeMapper;
import com.sjt.crm.dao.UserMapper;
import com.sjt.crm.dto.UserDto;
import com.sjt.crm.po.CustomerServe;
import com.sjt.crm.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Administrator on 2019/4/24.
 */
@Service
public class CustomerServeService extends BaseService<CustomerServe> {
    @Autowired
    private CustomerServeMapper customerServeMapper;
    @Autowired
    private UserMapper userMapper;

    public void saveOrUpdateServe(CustomerServe customerServe,Integer userId){
        //参数非空校验
        customerServe.setUpdateDate(new Date());
        UserDto userDto = userMapper.queryById(userId);
        if(customerServe.getId()==null){
            //说明是添加
            customerServe.setCreateDate(new Date());
            customerServe.setCreatePeople(userDto.getUserName());
            customerServe.setState("1");
            customerServe.setIsValid(1);
            AssertUtil.isTrue(customerServeMapper.save(customerServe)<1, CrmConstant.OPS_FAILED_MSG);
        }else{
            String state = customerServe.getState();
            if(state.equals("1")){
                customerServe.setState("2");
                customerServe.setAssignTime(new Date());// 分配时间
            }else if(state.equals("2")){
                customerServe.setState("3");
                customerServe.setServiceProceTime(new Date());//处理时间
                customerServe.setServiceProcePeople(userDto.getUserName());//处理人
            }else if(state.equals("3")){
                customerServe.setState("4");
            }

            AssertUtil.isTrue(customerServeMapper.update(customerServe)<1, CrmConstant.OPS_FAILED_MSG);
        }

    }
}
