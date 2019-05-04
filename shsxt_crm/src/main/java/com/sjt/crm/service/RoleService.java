package com.sjt.crm.service;

import com.sjt.crm.base.BaseService;
import com.sjt.crm.constants.CrmConstant;
import com.sjt.crm.dao.ModuleMapper;
import com.sjt.crm.dao.PermissionMapper;
import com.sjt.crm.dao.RoleMapper;
import com.sjt.crm.dto.ModuleDto;
import com.sjt.crm.po.Permission;
import com.sjt.crm.po.Role;
import com.sjt.crm.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.color.CMMException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2019/4/22.
 */
@Service
public class RoleService extends BaseService<Role> {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private ModuleMapper moduleMapper;

    public void saveOrUpdateRole(Role role) {
        String roleName = role.getRoleName();
        AssertUtil.isTrue(StringUtils.isBlank(roleName), "角色名字为空");
        role.setUpdateDate(new Date());
        if (role.getId() == null) {
            //判断用户名是否唯一
            AssertUtil.isTrue(roleMapper.queryByRoleName(roleName) != null, "角色名已存在");
            role.setCreateDate(new Date());
            role.setIsValid(1);
            AssertUtil.isTrue(roleMapper.save(role) < 1, CrmConstant.OPS_FAILED_MSG);
        } else {
            //先判断是否存在
            if (!roleName.equals(roleMapper.queryById(role.getId()).getRoleName())) {
                AssertUtil.isTrue(roleMapper.queryByRoleName(roleName) != null, "角色名已存在");
            }
            //说明是删除
            AssertUtil.isTrue(roleMapper.update(role) < 1, CrmConstant.OPS_FAILED_MSG);
        }
    }

    public void deleteRole(Integer[] ids) {
        /**
         * 1.删除
         * 2.
         */
        if (ids.length>0||ids!=null){
            //删除role
            for (int i=0;i<ids.length;i++){
                AssertUtil.isTrue(roleMapper.updateByRole(ids[i])<1,CrmConstant.OPS_FAILED_MSG);
                //查找user_role表中有多少条数据
                Integer total1 = roleMapper.queryByUserRoleId(ids[i]);
                if(total1!=0){
                    AssertUtil.isTrue(roleMapper.deleteUserRoleId(ids[i])<total1,CrmConstant.OPS_FAILED_MSG);
                }
                //删除模块和角色权限表中的东西
                Integer total2= roleMapper.queryByPerRoleId(ids[i]);
                if(total2!=0){
                    AssertUtil.isTrue(roleMapper.deletePerRoleId(ids[i])<total2,CrmConstant.OPS_FAILED_MSG);
                }
            }
        }
    }
    public List<ModuleDto> queryTree(Integer roleId){
        return roleMapper.queryTree(roleId);
    }

    public void doGrand(Integer roleId,Integer[] moduleIds){
        /**
         * 1.先查找
         * 2.删除
         * 3.批量添加
         */
        Integer total = permissionMapper.queryByRoleId(roleId);
        if(total>0){
            //则去删除
            AssertUtil.isTrue(permissionMapper.deleteByRoleId(roleId)<total, CrmConstant.OPS_FAILED_MSG);
        }
        if(moduleIds!=null&&moduleIds.length>0){
            List<Permission> list=new ArrayList<>();
            for (Integer moduleId:moduleIds) {
                Permission permission=new Permission();
                permission.setCreateDate(new Date());
                permission.setRoleId(roleId);
                permission.setModuleId(moduleId);
                permission.setAclValue(moduleMapper.queryById(moduleId).getOptValue());
                permission.setUpdateDate(new Date());
                list.add(permission);
            }
            AssertUtil.isTrue(permissionMapper.saveBatch(list)<list.size(),CrmConstant.OPS_FAILED_MSG);
        }

    }
}
