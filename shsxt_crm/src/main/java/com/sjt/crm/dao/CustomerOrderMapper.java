package com.sjt.crm.dao;

import com.sjt.crm.base.BaseDao;
import com.sjt.crm.po.Customer;
import com.sjt.crm.po.CustomerOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerOrderMapper extends BaseDao<CustomerOrder>{
}