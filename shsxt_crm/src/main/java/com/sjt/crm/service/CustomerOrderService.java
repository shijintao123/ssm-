package com.sjt.crm.service;

import com.sjt.crm.base.BaseService;
import com.sjt.crm.dao.CustomerMapper;
import com.sjt.crm.dao.CustomerOrderMapper;
import com.sjt.crm.po.CustomerOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/4/23.
 */
@Service
public class CustomerOrderService extends BaseService<CustomerOrder> {
    @Autowired
    private CustomerOrderMapper customerOrderMapper;
}
