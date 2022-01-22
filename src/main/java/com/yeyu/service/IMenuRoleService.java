package com.yeyu.service;

import com.yeyu.pojo.MenuRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yeyu.pojo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yeyu
 * @since 2021-11-14
 */
public interface IMenuRoleService extends IService<MenuRole> {

    RespBean updateMenuRole(Integer rid, Integer[] mids);
}
