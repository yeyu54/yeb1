package com.yeyu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeyu.pojo.Menu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yeyu
 * @since 2021-11-14
 */
public interface IMenuService extends IService<Menu> {
    List<Menu> getmenu();
    List<Menu> getmenuwithrole();
}
