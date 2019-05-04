package com.sjt.crm.service;

import com.sjt.crm.base.BaseService;
import com.sjt.crm.dao.CusDevPlanMapper;
import com.sjt.crm.dao.SaleChanceMapper;
import com.sjt.crm.po.CusDevPlan;
import com.sjt.crm.po.SaleChance;
import com.sjt.crm.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Administrator on 2019/4/19.
 */
@Service
public class CusDevPlanService extends BaseService<CusDevPlan> {
    @Autowired
    private CusDevPlanMapper cusDevPlanMapper;

    @Autowired
    private SaleChanceMapper saleChanceMapper;

    public void saveOrUpdateCusDevPlan(CusDevPlan cusDevPlan){
        //验证参数非空
        AssertUtil.isTrue(StringUtils.isBlank(cusDevPlan.getExeAffect()),"执行结果为空");
        AssertUtil.isTrue(StringUtils.isBlank(cusDevPlan.getPlanItem()),"计划内容为空");
        AssertUtil.isTrue(cusDevPlan.getPlanDate()==null,"计划时间为空");
        cusDevPlan.setUpdateDate(new Date());
        if(cusDevPlan.getId()==null){
            cusDevPlan.setCreateDate(new Date());
            cusDevPlan.setIsValid(1);
            //说明是添加
            AssertUtil.isTrue(cusDevPlanMapper.save(cusDevPlan)<1,"添加失败");
            //添加了则需要修改devresult状态码
            //先查询devresult状态码
            SaleChance saleChance = saleChanceMapper.queryById(cusDevPlan.getSaleChanceId());
            if(saleChance.getDevResult()==0){
                //做操作,修改状态码
                saleChance.setDevResult(1);
                AssertUtil.isTrue(saleChanceMapper.update(saleChance)<1,"修改失败");
            }
        }else{
            //说明是修改
            AssertUtil.isTrue(cusDevPlanMapper.update(cusDevPlan)<1,"修改失败");
        }
    }
}
