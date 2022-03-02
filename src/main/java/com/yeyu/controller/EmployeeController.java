package com.yeyu.controller;


import com.yeyu.pojo.Employee;
import com.yeyu.pojo.RespPage;
import com.yeyu.service.impl.EmployeeServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yeyu
 * @since 2021-11-14
 */
@RestController
@RequestMapping("/employee/basic")
public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeService;

    @ApiOperation(value = "获取所有员工分页")
    @GetMapping("/")
    public RespPage getEmployee(@RequestParam(defaultValue = "1") Integer currenPage,
                                @RequestParam(defaultValue = "10") Integer size,
                                Employee employee,
                                LocalDate[] beginDateScope){
        return employeeService.getEmployeeBypage(currenPage,size,employee,beginDateScope);
    }
}
