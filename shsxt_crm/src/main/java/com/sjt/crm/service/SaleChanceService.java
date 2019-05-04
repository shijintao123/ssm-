package com.sjt.crm.service;

import com.sjt.crm.base.BaseService;
import com.sjt.crm.dao.SaleChanceMapper;
import com.sjt.crm.dao.UserMapper;
import com.sjt.crm.po.SaleChance;
import com.sjt.crm.po.User;
import com.sjt.crm.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Administrator on 2019/4/18.
 */
@Service
public class SaleChanceService extends BaseService<SaleChance> {
    @Autowired
    private SaleChanceMapper saleChanceMapper;
    @Autowired
    private UserMapper userMapper;

    public void addOrUpdateSaleChance(SaleChance saleChance,Integer userId){
        AssertUtil.isTrue(StringUtils.isBlank(saleChance.getLinkMan()),"联系人为空");
        AssertUtil.isTrue(StringUtils.isBlank(saleChance.getCustomerName()),"客户名称为空");
        AssertUtil.isTrue(StringUtils.isBlank(saleChance.getLinkPhone()),"联系电话为空");
        //通过验证
        saleChance.setUpdateDate(new Date());
        String assignMan = saleChance.getAssignMan();

        if(StringUtils.isNotBlank(assignMan)){
            //已分配
            saleChance.setState(1);
            saleChance.setAssignTime(new Date());
        }else{
            saleChance.setState(0);
        }
        if(saleChance.getId()==null){
            //说明是添加
            User user = userMapper.queryById(userId);
            saleChance.setIsValid(1);
            saleChance.setDevResult(0);
            saleChance.setCreateDate(new Date());
            saleChance.setCreateMan(user.getTrueName());
            AssertUtil.isTrue(saleChanceMapper.save(saleChance)<1,"添加失败");
        }else{
            //说明是修改
            AssertUtil.isTrue(saleChanceMapper.update(saleChance)<1,"修改失败");
        }
    }
}
