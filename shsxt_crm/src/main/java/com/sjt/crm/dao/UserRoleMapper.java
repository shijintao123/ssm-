package com.sjt.crm.dao;

import com.sjt.crm.base.BaseDao;
import com.sjt.crm.po.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleMapper extends BaseDao<UserRole> {
    Integer queryCountByUserId(Integer userId);

    Integer deleteByUserId(Integer userId);
}