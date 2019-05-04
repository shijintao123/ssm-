package com.sjt.crm.service;

import com.sjt.crm.base.BaseService;
import com.sjt.crm.dao.OrderDetailsMapper;
import com.sjt.crm.po.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/4/24.
 */
@Service
public class OrderDetailsService extends BaseService<OrderDetails> {
    @Autowired
    private OrderDetailsMapper orderDetailsMapper;
}
