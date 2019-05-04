package com.sjt.crm.controller;

import com.sjt.crm.annotation.RequestPermission;
import com.sjt.crm.base.BaseController;
import com.sjt.crm.model.ResultInfo;
import com.sjt.crm.po.SaleChance;
import com.sjt.crm.query.SaleChanceQuery;
import com.sjt.crm.service.SaleChanceService;
import com.sjt.crm.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/18.
 */
@Controller
@RequestMapping("saleChance")
public class SaleChanceController extends BaseController {
    @Autowired
    private SaleChanceService saleChanceService;

    @RequestMapping("index/{state}")
    public String index(@PathVariable Integer state){
        if(state==1){
            return "cus_dev_plan";
        }else if(state==0){

            return "sale_chance";
        }
        return "error";
    }
    @RequestPermission(aclValue = "101001")
    @RequestMapping("querySaleChanceByParams")
    @ResponseBody
    public Map<String, Object> querySaleChanceParams(SaleChanceQuery saleChance,
                                                     @RequestParam(defaultValue = "1")Integer page,
                                                     @RequestParam(defaultValue = "10")Integer rows){
        saleChance.setPageNum(page);
        saleChance.setPageSize(rows);
        return saleChanceService.queryForPage(saleChance);
    }
    @RequestMapping("addOrUpdateSaleChance")
    @ResponseBody
    public ResultInfo addOrUpdateSaleChance(SaleChance saleChance, HttpServletRequest request){
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        saleChanceService.addOrUpdateSaleChance(saleChance,userId);
        return success(200,"操作成功");
    }
    @RequestMapping("deleteBatch")
    @ResponseBody
    public ResultInfo deleteBatch(Integer[] ids){
        saleChanceService.deleteBatch(ids);
        return success("操作成功");
    }
    @RequestMapping("updateSaleChance")
    @ResponseBody
    public ResultInfo updateSaleChance(SaleChance saleChance){
        saleChanceService.update(saleChance);
        return success("操作成功");
    }
}
