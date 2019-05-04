package com.sjt.crm.service;

import com.sjt.crm.base.BaseService;
import com.sjt.crm.dao.PermissionMapper;
import com.sjt.crm.po.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/4/23.
 */
@Service
public class PermissionService extends BaseService<Permission> {
    @Autowired
    private PermissionMapper permissionMapper;
    public List<String> queryPermissionByUserId(Integer id){
        return permissionMapper.queryPermissionByUserId(id);
    }
}
