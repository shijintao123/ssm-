package com.sjt.crm.service;

import com.sjt.crm.base.BaseService;
import com.sjt.crm.constants.CrmConstant;
import com.sjt.crm.dao.CustomerLossMapper;
import com.sjt.crm.dao.CustomerMapper;
import com.sjt.crm.po.Customer;
import com.sjt.crm.po.CustomerLoss;
import com.sjt.crm.utils.AssertUtil;
import com.sjt.crm.utils.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/23.
 */
@Service
public class CustomerService extends BaseService<Customer> {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerLossMapper CustomerlossMapper;

    public List<Map> queryDataDicsByDicName(String name){
        return customerMapper.queryDataDicsByDicName(name);
    }

    public void saveOrUpdateCustomer(Customer customer){
        //校验参数是否为空.....
        customer.setUpdateDate(new Date());
        if(customer.getId()==null){
            //说明添加
            customer.setCreateDate(new Date());
            customer.setIsValid(1);
            customer.setState(0);
            //生成用户编号
            customer.setKhno(MathUtil.genereateKhCode());
            AssertUtil.isTrue(customerMapper.save(customer)<1, CrmConstant.OPS_FAILED_MSG);
        }else{
            AssertUtil.isTrue(customerMapper.update(customer)<1, CrmConstant.OPS_FAILED_MSG);
        }
    }

    public void saveCustomerLoss() {
        List<Customer> customers = customerMapper.queryCustomerLoss();
        System.out.println(customers);
        if (!CollectionUtils.isEmpty(customers)) {
            List<CustomerLoss> list = new ArrayList<>();
            for (Customer c : customers) {
                Integer id = c.getId();
                CustomerLoss customerLoss = new CustomerLoss();
                customerLoss.setCusManager(c.getCusManager());
                customerLoss.setIsValid(1);
                customerLoss.setState(0);
                customerLoss.setCusNo(c.getKhno());
                customerLoss.setCusName(c.getName());
                customerLoss.setCreateDate(new Date());
                customerLoss.setUpdateDate(new Date());
                //批量添加
                list.add(customerLoss);
            }
            AssertUtil.isTrue(customerMapper.updateBatchState(customers) < customers.size(), CrmConstant.OPS_FAILED_MSG);
            AssertUtil.isTrue(CustomerlossMapper.saveBatch(list) < list.size(), CrmConstant.OPS_FAILED_MSG);
        }
    }
    public List<Map> queryEchartByData(){
         return customerMapper.queryEchartByData();
    }
    public List<Map> queryEchartByServer(){
        return customerMapper.queryEchartByServer();
    }
}
