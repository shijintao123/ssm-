package com.sjt.crm.controller;

import com.sjt.crm.base.BaseController;
import com.sjt.crm.dao.SaleChanceMapper;
import com.sjt.crm.model.ResultInfo;
import com.sjt.crm.po.CusDevPlan;
import com.sjt.crm.po.SaleChance;
import com.sjt.crm.query.CusDevPlanQuery;
import com.sjt.crm.query.SaleChanceQuery;
import com.sjt.crm.service.CusDevPlanService;
import com.sjt.crm.service.SaleChanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Administrator on 2019/4/19.
 */
@Controller
@RequestMapping("cusDevPlan")
public class CusDevPlanController extends BaseController {
    @Autowired
    private SaleChanceService saleChanceService;
    @Autowired
    private CusDevPlanService cusDevPlanService;
    @RequestMapping("index")
    public String index(Integer sid,Model model){
        SaleChance saleChance = saleChanceService.queryById(sid);
        model.addAttribute(saleChance);
        return "cus_dev_plan_detail";
    }
    @RequestMapping("queryCusDevPlanByParams")
    @ResponseBody
    public Map<String, Object> queryCusDevPlanByParams(CusDevPlanQuery cusDevPlanQuery,
                                                     @RequestParam(defaultValue = "1")Integer page,
                                                     @RequestParam(defaultValue = "10")Integer rows){
        cusDevPlanQuery.setPageNum(page);
        cusDevPlanQuery.setPageSize(rows);
        return cusDevPlanService.queryForPage(cusDevPlanQuery);
    }
    @RequestMapping("saveOrUpdateCusDevPlan")
    @ResponseBody
    public ResultInfo saveOrUpdateCusDevPlan(CusDevPlan cusDevPlan){
        cusDevPlanService.saveOrUpdateCusDevPlan(cusDevPlan);
        return success("操作成功");
    }
    @RequestMapping("deleteBatch")
    @ResponseBody
    public ResultInfo deleteBatch(Integer[] ids){
        cusDevPlanService.deleteBatch(ids);
        return success("操作成功");
    }
}
