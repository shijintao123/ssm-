package com.sjt.crm.controller;

import com.sjt.crm.base.BaseController;
import com.sjt.crm.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Administrator on 2019/4/20.
 */
@Controller
public class UserRoleController extends BaseController {
    @Autowired
    private UserRoleService userRoleService;
}
