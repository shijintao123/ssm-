package com.sjt.crm.controller;

import com.sjt.crm.base.BaseController;
import com.sjt.crm.base.BaseDao;
import com.sjt.crm.constants.CrmConstant;
import com.sjt.crm.model.ResultInfo;
import com.sjt.crm.po.Module;
import com.sjt.crm.query.ModuleQuery;
import com.sjt.crm.query.UserQuery;
import com.sjt.crm.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/23.
 */
@Controller
@RequestMapping("module")
public class ModuleController extends BaseController {
    @Autowired
    private ModuleService moduleService;
    @RequestMapping("index")
    public String index(){
        return "module";
    }
    @RequestMapping("queryModuleByParams")
    @ResponseBody
    public Map<String, Object> queryModuleByParams(ModuleQuery moduleQuery,
                                                 @RequestParam(defaultValue = "1")Integer page,
                                                 @RequestParam(defaultValue = "10")Integer rows){
        moduleQuery.setPageNum(page);
        moduleQuery.setPageSize(rows);
        return moduleService.queryForPage(moduleQuery);
    }
    @RequestMapping("queryByModuleGrade")
    @ResponseBody
    public List<Map> queryByModuleGrade(Integer grade){
        return moduleService.queryByModuleGrade(grade);
    }
    @RequestMapping("saveOrUpdateModule")
    @ResponseBody
    public ResultInfo saveOrUpdateModule(Module module){
        moduleService.saveOrUpdateModule(module);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }
    @RequestMapping("deleteModule")
    @ResponseBody
    public ResultInfo deleteModule(String optValue){
        moduleService.deleteModule(optValue);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }
}
