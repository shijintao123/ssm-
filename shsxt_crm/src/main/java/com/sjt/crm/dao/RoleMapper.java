package com.sjt.crm.dao;

import com.sjt.crm.base.BaseDao;
import com.sjt.crm.dto.ModuleDto;
import com.sjt.crm.po.Module;
import com.sjt.crm.po.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper extends BaseDao<Role> {
    Role queryByRoleName(String roleName);

    List<ModuleDto> queryTree(Integer roleId);

    Integer updateByRole(Integer roleId);
    Integer queryByUserRoleId(Integer roleId);
    Integer deleteUserRoleId(Integer roleId);
    Integer queryByPerRoleId(Integer roleId);
    Integer deletePerRoleId(Integer roleId);
}