package com.yeyu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yeyu.mapper.MenuMapper;
import com.yeyu.pojo.Admin;
import com.yeyu.pojo.Menu;
import com.yeyu.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yeyu
 * @since 2021-11-14
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper mapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public List<Menu> getmenu(){
        Integer adminid = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        List<Menu> menus = (List<Menu>)valueOperations.get("menu_"+adminid);
        if (CollectionUtils.isEmpty(menus)){
            menus  =    mapper.getmenuid(adminid);
            valueOperations.set("menu_"+adminid,menus);
        }
        return menus;
    }
    @Override
    public List<Menu> getmenuwithrole(){
        return mapper.getmenuwithrole();
    }
}
