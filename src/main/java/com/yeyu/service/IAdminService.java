package com.yeyu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yeyu.pojo.Admin;
import com.yeyu.pojo.RespBean;
import com.yeyu.pojo.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yeyu
 * @since 2021-11-14
 */
public interface IAdminService extends IService<Admin> {
    /**
    * @Description:登陆方法
    * @Param: [username, password, code, request]
    * @return
    * @date: 2022/2/8
    */
    RespBean login(String username, String password,String code, HttpServletRequest request);
    /**
    * @Description:获取登陆的用户名
    * @Param: [username]
    * @return
    * @date: 2022/2/8
    */
    Admin getAdminusername(String username);
    /**
    * @Description:获取登录角色的权限
    * @Param: [adminId]
    * @return
    * @date: 2022/2/8
    */
    List<Role> getrole(Integer adminId);
    /**
    * @Description:获取所有操作员
    * @Param: [keywords]
    * @return
    * @date: 2022/2/8
    */
    List<Admin> getAllAdmin(String keywords);
    /**
    * @Description:
    * @Param: [adminid,rids[]]
    * @return
    * @date: 2022/2/8
    */
    RespBean addAdminRole(Integer adminid,Integer[] rids);
}
