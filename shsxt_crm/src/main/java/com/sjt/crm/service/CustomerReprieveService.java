package com.sjt.crm.service;

import com.sjt.crm.base.BaseService;
import com.sjt.crm.constants.CrmConstant;
import com.sjt.crm.dao.CustomerReprieveMapper;
import com.sjt.crm.po.CustomerReprieve;
import com.sjt.crm.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Administrator on 2019/4/24.
 */
@Service
public class CustomerReprieveService extends BaseService<CustomerReprieve> {
    @Autowired
    private CustomerReprieveMapper customerReprieveMapper;

    public void saveOrUpdateRep(CustomerReprieve customerReprieve,Integer lossId){
        AssertUtil.isTrue(StringUtils.isBlank(customerReprieve.getMeasure()),"措施为空");
        customerReprieve.setUpdateDate(new Date());
        if(customerReprieve.getId()==null){
            //说明是添加
            //补全参数
            customerReprieve.setCreateDate(new Date());
            customerReprieve.setIsValid(1);
            customerReprieve.setLossId(lossId);
            AssertUtil.isTrue(customerReprieveMapper.save(customerReprieve)<1, CrmConstant.OPS_SUCCESS_MSG);
        }else{
            AssertUtil.isTrue(customerReprieveMapper.update(customerReprieve)<1, CrmConstant.OPS_SUCCESS_MSG);
            //说明是修改
        }
    }
    public void  updateState(Integer id){
        AssertUtil.isTrue(customerReprieveMapper.updateState(id)<1,CrmConstant.OPS_FAILED_MSG);
    }
}
