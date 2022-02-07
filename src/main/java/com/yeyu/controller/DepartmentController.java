package com.yeyu.controller;


import com.yeyu.pojo.Department;
import com.yeyu.pojo.RespBean;
import com.yeyu.service.impl.DepartmentServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/system/basic/department")
public class DepartmentController {

    @Autowired
    private DepartmentServiceImpl departmentService;

    /**
    * @Description:获取部门
    * @Param: []
    * @return
    * @date: 2022/2/7
    */
    @ApiOperation(value = "获取所有部门")
    @GetMapping("/")
    public List<Department> getDepart(){
        return departmentService.getAll();
    }
    /**
    * @Description:添加部门
    * @Param: [dep]
    * @return
    * @date: 2022/2/7
    */
    @ApiOperation(value = "添加部门")
    @PostMapping("/")
    public RespBean addDepart(@RequestBody Department dep){
        return departmentService.addDepart(dep);
    }

    @ApiOperation(value = "删除部门")
    @DeleteMapping("/")
    public RespBean delDepart(Integer id){
        return departmentService.delDepart(id);
    }
}
