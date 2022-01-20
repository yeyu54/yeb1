package com.yeyu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yeyu.pojo.Menu;
import com.yeyu.pojo.MenuRole;
import com.yeyu.pojo.Position;
import com.yeyu.pojo.RespBean;
import com.yeyu.service.IMenuRoleService;
import com.yeyu.service.IMenuService;
import com.yeyu.service.IPositionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yeyu
 * @since 2021-11-14
 */
@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {

    @Autowired
    private IPositionService positionService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IMenuRoleService menuRoleService;

    @ApiOperation(value = "获取所有数据信息")
    @GetMapping("/")
    public List<Position> getpostion(){
        return positionService.list();
    }

    @ApiOperation(value = "添加职位信息")
    @PostMapping("/")
    public RespBean addpositon(@RequestBody Position position){
        position.setCreate_date(LocalDateTime.now());
        if(positionService.save(position)){
            return RespBean.success("添加成功!");
        }
        return RespBean.error("添加失败！");
    }
    @ApiOperation(value = "更新职位信息")
    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position){
        if (positionService.updateById(position)){
            return RespBean.success("修改成功");
        }
        return RespBean.error("修改失败");
    }
    @ApiOperation(value = "删除职位")
    @DeleteMapping("/{id}")
    public RespBean delePostion(@PathVariable Integer id){
        if (positionService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }
    @ApiOperation(value = "批量删除职位信息")
    @DeleteMapping("/")
    public RespBean delePostionByids(Integer[] ids){

        if (positionService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败！");
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
    public RespBean updateRole(){

        return RespBean.error("更新失败");
    }
}
