package com.sjt.crm.dao;

import com.sjt.crm.base.BaseDao;
import com.sjt.crm.po.CustomerReprieve;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerReprieveMapper extends BaseDao<CustomerReprieve> {
    Integer updateState(Integer id);
}