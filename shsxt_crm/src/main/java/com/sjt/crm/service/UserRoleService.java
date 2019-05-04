package com.sjt.crm.service;

import com.sjt.crm.base.BaseService;
import com.sjt.crm.dao.UserRoleMapper;
import com.sjt.crm.po.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/4/20.
 */
@Service
public class UserRoleService extends BaseService<UserRole> {
    @Autowired
    private UserRoleMapper userRoleMapper;
}
