package com.sjt.crm.dao;

import com.sjt.crm.base.BaseDao;
import com.sjt.crm.po.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper extends BaseDao<Permission> {
    Integer queryByRoleId(Integer roleId);
    Integer deleteByRoleId(Integer roleId);

    Integer queryCountByOpValue(String optValue);

    Integer deleteByOpValue(String optValue);

    List<String> queryPermissionByUserId(Integer id);
}