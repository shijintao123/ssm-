package com.sjt.crm.controller;

import com.sjt.crm.base.BaseController;
import com.sjt.crm.constants.CrmConstant;
import com.sjt.crm.model.ResultInfo;
import com.sjt.crm.po.CustomerLoss;
import com.sjt.crm.po.CustomerReprieve;
import com.sjt.crm.query.CusReprieveQuery;
import com.sjt.crm.query.UserQuery;
import com.sjt.crm.service.CustomerLossService;
import com.sjt.crm.service.CustomerReprieveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Administrator on 2019/4/24.
 */
@Controller
@RequestMapping("customerReprieve")
public class CustomerReprieveController extends BaseController {
    @Autowired
    private CustomerReprieveService customerReprieveService;
    @Autowired
    private CustomerLossService customerLossService;
    @RequestMapping("index")
    public String index(Integer id,Model model){
        CustomerLoss customerLoss = customerLossService.queryById(id);
        model.addAttribute("customerLoss",customerLoss);
        return "customer_loss_reprieve";
    }
    @RequestMapping("queryCusReprieveByParams")
    @ResponseBody
    public Map<String, Object> queryCusReprieveByParams(CusReprieveQuery query,
                                                 @RequestParam(defaultValue = "1")Integer page,
                                                 @RequestParam(defaultValue = "10")Integer rows){
        query.setPageNum(page);
        query.setPageSize(rows);
        return customerReprieveService.queryForPage(query);
    }

    @RequestMapping("saveOrUpdateRep")
    @ResponseBody
    public ResultInfo saveOrUpdateRep(CustomerReprieve customerReprieve,Integer lossId){
        customerReprieveService.saveOrUpdateRep(customerReprieve,lossId);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }
    @RequestMapping("deleteBatch")
    @ResponseBody
    public ResultInfo deleteBatch(Integer[] ids){
        customerReprieveService.deleteBatch(ids);
        return success("操作成功");
    }
    @RequestMapping("updateState")
    @ResponseBody
    public ResultInfo updateState(Integer lossId){
        customerReprieveService.updateState(lossId);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }
}
