package com.yeyu.controller;


import com.yeyu.pojo.Admin;
import com.yeyu.pojo.RespBean;
import com.yeyu.pojo.Role;
import com.yeyu.service.impl.AdminServiceImpl;
import com.yeyu.service.impl.RoleServiceImpl;
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
@RequestMapping("/system/admin")
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;
    @Autowired
    private RoleServiceImpl roleService;
    /**
    * @Description:获取管理员信息
    * @Param: [keywords]
    * @return
    * @date: 2022/2/8
    */
    @ApiOperation(value = "获取所有操作员")
    @GetMapping("/")
    public List<Admin> getAllAdmin(String keywords){
        return adminService.getAllAdmin(keywords);
    }
    /**
    * @Description:更新操作员
    * @Param: [admin]
    * @return
    * @date: 2022/2/8
    */
    @ApiOperation(value = "更新操作员")
    @PutMapping("/")
    public RespBean updateAdmin(@RequestBody Admin admin){
        if (adminService.updateById(admin)){
            return RespBean.success("更新成功",admin);
        }
        return RespBean.error("更新失败");
    }
    /**
    * @Description: 删除操作员
    * @Param: [id]
    * @return
    * @date: 2022/2/8
    */
    @ApiOperation(value = "删除操作员")
    @DeleteMapping("/")
    public RespBean deleteAdmin(@PathVariable Integer id){
    if (adminService.removeById(id)){
        return RespBean.success("删除成功");
    }
    return RespBean.error("删除失败");
    }
    /**
    * @Description:获取所有角色
    * @Param: []
    * @return
    * @date: 2022/2/8
    */
    @ApiOperation(value = "获取所有角色")
    @GetMapping("/roles")
    public List<Role> getAllRoles(){
        return roleService.list();
    }
    @ApiOperation(value = "添加操作员角色")
    @PutMapping ("/role")
    public RespBean updateRole(Integer adminid,Integer[] rids){
        return adminService.addAdminRole(adminid,rids);
    }
}
