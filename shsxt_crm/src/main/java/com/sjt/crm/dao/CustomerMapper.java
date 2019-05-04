package com.sjt.crm.dao;

import com.sjt.crm.base.BaseDao;
import com.sjt.crm.po.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CustomerMapper extends BaseDao<Customer>{

    public List<Map> queryDataDicsByDicName(String name);

    List<Customer> queryCustomerLoss();
    public  Integer updateBatchState(List<Customer> list);

    List<Map> queryEchartByData();
    List<Map> queryEchartByServer();
}
