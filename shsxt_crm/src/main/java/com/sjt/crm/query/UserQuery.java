package com.sjt.crm.query;

import com.sjt.crm.base.BaseQuery;
import com.sjt.crm.po.User;

/**
 * Created by Administrator on 2019/4/20.
 */
public class UserQuery  extends BaseQuery{
    private String userName;

    private String email;

    private String phone;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
