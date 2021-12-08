package com.yeyu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yeyu.mapper.MenuMapper;
import com.yeyu.pojo.Admin;
import com.yeyu.pojo.Menu;
import com.yeyu.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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

    @Override
    public List<Menu> getmenu(){
        return mapper.getmenuid(((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }
}
