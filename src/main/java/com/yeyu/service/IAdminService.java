package com.yeyu.service;

import com.yeyu.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yeyu.pojo.RespBean;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yeyu
 * @since 2021-11-14
 */
public interface IAdminService extends IService<Admin> {

    RespBean login(String username, String password,String code, HttpServletRequest request);

    Admin getAdminusername(String username);
}
