package com.sjt.crm.dao;

import com.sjt.crm.base.BaseDao;
import com.sjt.crm.po.Module;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ModuleMapper extends BaseDao<Module>{
    List<Map> queryByModuleGrade(Integer grade);
    Module queryModuleByOpt(String optValue);
    Module queryModuleByName(String ModuleName);

    Integer deleteByOpValue(String optValue);

    Integer queryCountByOpValue(String optValue);
}