package com.sjt.crm.query;

import com.sjt.crm.base.BaseQuery;

/**
 * Created by Administrator on 2019/4/23.
 */
public class CustomerOrderQuery extends BaseQuery{
    private  Integer cusId;

    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }
}
