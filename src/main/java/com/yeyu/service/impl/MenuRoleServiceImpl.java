package com.yeyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yeyu.mapper.MenuRoleMapper;
import com.yeyu.pojo.MenuRole;
import com.yeyu.pojo.RespBean;
import com.yeyu.service.IMenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yeyu
 * @since 2021-11-14
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {

    @Autowired
    private MenuRoleMapper mapper;
    /**
    * @Description:更新角色菜单
    * @Param: [rid, mids]
    * @return
    * @date: 2022/1/21
    */
    @Override
    public RespBean updateMenuRole(Integer rid,Integer[] mids){
        mapper.delete(new QueryWrapper<MenuRole>().eq("rid",rid));
        if (null == mids || 0==mids.length){
            return RespBean.success("更新成功");
        }
        Integer result = mapper.insertRecord(rid, mids);
        if (result == mids.length){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }
}
