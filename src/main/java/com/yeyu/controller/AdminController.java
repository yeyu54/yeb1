package com.yeyu.controller;


import com.yeyu.pojo.Admin;
import com.yeyu.service.impl.AdminServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yeyu
 * @since 2021-11-14
 */
@RestController
@RequestMapping("/system/admin")
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    @ApiOperation(value = "获取所有操作员")
    public List<Admin> getAll(String keywords){
        return adminService.getAll(keywords);
    }
}
