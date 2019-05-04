package com.sjt.crm.task;

import com.sjt.crm.po.Customer;
import com.sjt.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2019/4/24.
 */
//@Component
public class TaskJob  {

    @Autowired
    private CustomerService customerService;
    //@Scheduled(cron = "0/5 * * * * ? ")
    public void job(){
        System.out.println("定时任务开启========================");
        customerService.saveCustomerLoss();
        System.out.println("定时任务关闭========================");
    }
}
