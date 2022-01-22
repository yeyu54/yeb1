package com.yeyu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yeyu.pojo.Menu;
import com.yeyu.pojo.MenuRole;
import com.yeyu.pojo.RespBean;
import com.yeyu.pojo.Role;
import com.yeyu.service.IMenuRoleService;
import com.yeyu.service.IMenuService;
import com.yeyu.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 13474
 * @Package com.yeyu.controller
 * @date 2022/1/1814:28
 */
@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IMenuRoleService menuRoleService;

    @ApiOperation(value = "获取所有角色")
    @GetMapping("/")
    public List<Role> getAllRole(){
        System.out.println(roleService.list());
        return roleService.list();

    }

    @ApiOperation(value = "添加角色")
    @PostMapping("/")
    public RespBean addRloe(@RequestBody Role role){
        if (!role.getName().startsWith("ROLE_")){
            role.setName("ROLE_"+role.getName());
        }
        if (roleService.save(role)){
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }
    @ApiOperation(value = "删除角色")
    @DeleteMapping("/role/{rid}")
    public RespBean deleteRole(Integer rid){
        if (roleService.removeById(rid)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation(value = "查询所有菜单")
    @GetMapping("/menus")
    public List<Menu> getAllMenu(){
        return menuService.getAllMenus();
    }

    @ApiOperation(value = "根据角色id查询角色")
    @GetMapping("/mid/{rid}")
    public List<Integer> getMenuByid(@PathVariable Integer rid){
        return menuRoleService.list(new QueryWrapper<MenuRole>().
                eq("rid",rid)).stream().
                map(MenuRole::getMid).
                collect(Collectors.toList());
    }
    @ApiOperation(value = "更新角色菜单")
    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid, Integer[] mids){
        return menuRoleService.updateMenuRole(rid,mids);
    }
    
}
