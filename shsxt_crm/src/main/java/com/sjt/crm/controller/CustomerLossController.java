package com.sjt.crm.controller;

import com.sjt.crm.base.BaseController;
import com.sjt.crm.po.CustomerLoss;
import com.sjt.crm.query.CustomerLossQuery;
import com.sjt.crm.query.UserQuery;
import com.sjt.crm.service.CustomerLossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Administrator on 2019/4/24.
 */
@Controller
@RequestMapping("customerLoss")
public class CustomerLossController extends BaseController {
    @Autowired
    private CustomerLossService customerLossService;
    @RequestMapping("index")
    public String index(){
        return "customer_loss";
    }
    @RequestMapping("queryCustomerLossByParams")
    @ResponseBody
    public Map<String, Object> queryCustomerLossByParams(CustomerLossQuery customerLossQuery,
                                                         @RequestParam(defaultValue = "1")Integer page,
                                                         @RequestParam(defaultValue = "10")Integer rows){
        customerLossQuery.setPageNum(page);
        customerLossQuery.setPageSize(rows);
        return customerLossService.queryForPage(customerLossQuery);
    }
}
