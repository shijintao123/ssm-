package com.sjt.crm.service;

import com.sjt.crm.base.BaseService;
import com.sjt.crm.dao.CustomerLossMapper;
import com.sjt.crm.po.CustomerLoss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/4/24.
 */
@Service
public class CustomerLossService extends BaseService<CustomerLoss> {
    @Autowired
    private CustomerLossMapper customerLossMapper;
}
