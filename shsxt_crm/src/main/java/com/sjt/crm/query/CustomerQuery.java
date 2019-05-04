package com.sjt.crm.query;

import com.sjt.crm.base.BaseQuery;

/**
 * Created by Administrator on 2019/4/23.
 */

public class CustomerQuery extends BaseQuery {
    private  String name;//客户名称
    private String khno;//客户编号
    private String fr;//法人

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKhno() {
        return khno;
    }

    public void setKhno(String khno) {
        this.khno = khno;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }
}
