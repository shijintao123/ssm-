package com.sjt.crm.query;

import com.sjt.crm.base.BaseQuery;

/**
 * Created by Administrator on 2019/4/22.
 */
public class RoleQuery  extends BaseQuery{
    private String roleName;
    private String createDate;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
