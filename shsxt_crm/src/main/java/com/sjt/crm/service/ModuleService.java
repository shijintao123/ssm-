package com.sjt.crm.service;

import com.sjt.crm.base.BaseService;
import com.sjt.crm.constants.CrmConstant;
import com.sjt.crm.dao.ModuleMapper;
import com.sjt.crm.dao.PermissionMapper;
import com.sjt.crm.po.Module;
import com.sjt.crm.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/23.
 */
@Service
public class ModuleService extends BaseService<Module> {
    @Autowired
    private ModuleMapper moduleMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    public List<Map> queryByModuleGrade(Integer grade){
        return moduleMapper.queryByModuleGrade(grade);
    }

    public void saveOrUpdateModule(Module module){
        checkParams(module);
        module.setUpdateDate(new Date());
        if(module.getId()==null){
            module.setCreateDate(new Date());
            module.setIsValid((byte) 1);
            AssertUtil.isTrue(moduleMapper.save(module)<1, CrmConstant.OPS_FAILED_MSG);
        }else{
            //修改操作
        }

    }

    private void checkParams(Module module) {
        /**
         * 非空校验
         */
        String moduleName = module.getModuleName();
        String optValue = module.getOptValue();
        AssertUtil.isTrue(StringUtils.isBlank(moduleName),"模块名为空");
        AssertUtil.isTrue(StringUtils.isBlank(optValue),"权限码为空");
        /**
         * 唯一校验
         */
        AssertUtil.isTrue(moduleMapper.queryModuleByOpt(optValue)!=null,"权限码已存在");
        AssertUtil.isTrue(moduleMapper.queryModuleByName(moduleName)!=null,"模块名已存在");
        AssertUtil.isTrue(module.getGrade()==null,"层级为空");
        /**
         * 权限码位数校验
         */
        Integer grade = module.getGrade();
        String optValue1 = module.getOptValue();
        int i = (grade + 1) * 2;
        AssertUtil.isTrue(i!=optValue1.length(),"权限码位数不正确，应为"+i+"位");
        /**
         * 层级关系校验
         */
        if(module.getGrade()>0){
            AssertUtil.isTrue(module.getParentId()==null,"父层级为空");
            Module parent = moduleMapper.queryById(module.getParentId());
            Integer parentGrade = parent.getGrade();
            AssertUtil.isTrue((grade-parentGrade)!=1,"层级关系错误");
            /**
             * 权限码格式校验
             */
            String parentOptValue = parent.getOptValue();
            AssertUtil.isTrue(!optValue.startsWith(parentOptValue),"权限码格式错误,应为"+parentOptValue+"xx");
        }else{
            module.setParentId(null);
        }
    }
    public void deleteModule(String optValue){
        AssertUtil.isTrue(StringUtils.isBlank(optValue),"权限码为空");
        Integer total = moduleMapper.queryCountByOpValue(optValue);
        if(total>0){
            AssertUtil.isTrue(moduleMapper.deleteByOpValue(optValue)<total,CrmConstant.OPS_FAILED_MSG);
        }
        Integer total2 = permissionMapper.queryCountByOpValue(optValue);
        if(total2>0){
            AssertUtil.isTrue(permissionMapper.deleteByOpValue(optValue)<total2,CrmConstant.OPS_FAILED_MSG);
        }

    }
}
