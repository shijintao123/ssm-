package com.sjt.crm.controller;

import com.sjt.crm.base.BaseController;
import com.sjt.crm.po.Customer;
import com.sjt.crm.po.CustomerOrder;
import com.sjt.crm.query.CustomerOrderQuery;
import com.sjt.crm.query.CustomerQuery;
import com.sjt.crm.service.CustomerOrderService;
import com.sjt.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/23.
 */
@Controller
@RequestMapping("order")
public class CustomerOrderController extends BaseController{
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerOrderService customerOrderService;
    @RequestMapping("index")
    public String index(Integer cusId,Model model){
        Customer customer = customerService.queryById(cusId);
        model.addAttribute("customer",customer);
        return "customer_order";
    }
    @RequestMapping("queryOrdersByParams")
    @ResponseBody
    public Map<String, Object> queryOrdersByParams(CustomerOrderQuery CustomerOrderQuery,
                                                     @RequestParam(defaultValue = "1")Integer page,
                                                     @RequestParam(defaultValue = "10")Integer rows){
        CustomerOrderQuery.setPageNum(page);
        CustomerOrderQuery.setPageSize(rows);
        return customerOrderService.queryForPage(CustomerOrderQuery);
    }
}
