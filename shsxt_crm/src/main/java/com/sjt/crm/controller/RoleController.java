package com.sjt.crm.controller;

import com.sjt.crm.base.BaseController;
import com.sjt.crm.constants.CrmConstant;
import com.sjt.crm.dto.ModuleDto;
import com.sjt.crm.model.ResultInfo;
import com.sjt.crm.po.Role;
import com.sjt.crm.query.RoleQuery;
import com.sjt.crm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/22.
 */
@Controller
@RequestMapping("role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;
    @RequestMapping("index")
    public String index(){
        return "role";
    }
    @RequestMapping("queryRoleByParams")
    @ResponseBody
    public Map<String, Object> queryRoleByParams(RoleQuery roleQuery,
                                                 @RequestParam(defaultValue = "1")Integer page,
                                                 @RequestParam(defaultValue = "10")Integer rows){
        roleQuery.setPageNum(page);
        roleQuery.setPageSize(rows);
        return roleService.queryForPage(roleQuery);
    }
    @RequestMapping("saveOrUpdateRole")
    @ResponseBody
    public ResultInfo saveOrUpdateRole(Role role){
        roleService.saveOrUpdateRole(role);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }
    @RequestMapping("deleteBatch")
    @ResponseBody
    public ResultInfo deleteRole(Integer[] ids){
        roleService.deleteRole(ids);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }
    @RequestMapping("queryTree")
    @ResponseBody
    public List<ModuleDto> queryTree(Integer roleId){
        return roleService.queryTree(roleId);

    }
    @RequestMapping("doGrand")
    @ResponseBody
    public ResultInfo doGrand(Integer roleId, Integer[] moduleIds){
        roleService.doGrand(roleId,moduleIds);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }
}
