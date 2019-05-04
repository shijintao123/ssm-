package com.sjt.crm.controller;

import com.sjt.crm.base.BaseController;
import com.sjt.crm.po.OrderDetails;
import com.sjt.crm.query.CustomerOrderQuery;
import com.sjt.crm.query.OrderDetailsQuery;
import com.sjt.crm.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Administrator on 2019/4/24.
 */
@Controller
@RequestMapping("orderDetails") 
public class OrderDetailsController extends BaseController {
    @Autowired
    private OrderDetailsService orderDetailsService;

    @RequestMapping("queryOrdersDetailsByParams")
    @ResponseBody
    public Map<String, Object> queryOrdersDetailsByParams(OrderDetailsQuery orderDetailsQuery,
                                                   @RequestParam(defaultValue = "1")Integer page,
                                                   @RequestParam(defaultValue = "10")Integer rows){
        orderDetailsQuery.setPageNum(page);
        orderDetailsQuery.setPageSize(rows);
        return orderDetailsService.queryForPage(orderDetailsQuery);
    }
}
