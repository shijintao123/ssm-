package com.sjt.crm.query;

import com.sjt.crm.base.BaseQuery;

/**
 * Created by Administrator on 2019/4/18.
 */
public class SaleChanceQuery extends BaseQuery{
    private String customerName;//客户名称
    private Integer state; //状态码
    private Integer devResult; //开发结果
    private String createDate;//创建时间

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getDevResult() {
        return devResult;
    }

    public void setDevResult(Integer devResult) {
        this.devResult = devResult;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "SaleChanceQuery{" +
                "customerName='" + customerName + '\'' +
                ", state=" + state +
                ", devResult=" + devResult +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
