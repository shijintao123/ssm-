package com.sjt.crm.controller;

import com.sjt.crm.base.BaseController;
import com.sjt.crm.constants.CrmConstant;
import com.sjt.crm.model.ResultInfo;
import com.sjt.crm.po.Customer;
import com.sjt.crm.query.CustomerQuery;
import com.sjt.crm.query.UserQuery;
import com.sjt.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/23.
 */
@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController {
    @Autowired
    private CustomerService customerService;
    @RequestMapping("index")
    public String index(){
        return "customer";
    }

    @RequestMapping("queryCustomerByParams")
    @ResponseBody
    public Map<String, Object> queryCustomerByParams(CustomerQuery CustomerQuery,
                                                 @RequestParam(defaultValue = "1")Integer page,
                                                 @RequestParam(defaultValue = "10")Integer rows){
        CustomerQuery.setPageNum(page);
        CustomerQuery.setPageSize(rows);
        return customerService.queryForPage(CustomerQuery);
    }
    @RequestMapping("queryDataDicsByDicName")
    @ResponseBody
    public List<Map> queryDataDicsByDicName(String dicName){
        return customerService.queryDataDicsByDicName(dicName);
    }
    @RequestMapping("saveOrUpdateCustomer")
    @ResponseBody
    public ResultInfo saveOrUpdateCustomer(Customer customer){
        customerService.saveOrUpdateCustomer(customer);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }
    @RequestMapping("deleteCustomer")
    @ResponseBody
    public ResultInfo deleteBatch(Integer[] ids){
        customerService.deleteBatch(ids);
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }

    @RequestMapping("queryAllTest")
    @ResponseBody
    public ResultInfo saveCustomerLoss(){
        customerService.saveCustomerLoss();
        return success(CrmConstant.OPS_SUCCESS_MSG);
    }

    @RequestMapping("report/{code}")
    public String report(@PathVariable Integer code){
        if(code==0){

        }else if(code==1){
            return "report_struts";
        }else if(code==2){
            return "report_server";
        }
        return "error";
    }
    @RequestMapping("echartData")
    @ResponseBody
    public List<Map> queryEchartByData(){
        return customerService.queryEchartByData();
    }
    @RequestMapping("echartByServer")
    @ResponseBody
    public List<Map> queryEchartByServer(){
        return customerService.queryEchartByServer();
    }
}
