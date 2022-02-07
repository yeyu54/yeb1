package com.yeyu.utils;

import com.yeyu.pojo.Admin;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author 13474
 * @Package com.yeyu.utils
 * @date 2022/2/715:52
 */
public class AdminUtil {
    /**
    * @Description:获取当前操作员
    * @Param:
    * @return
    * @date: 2022/2/7
    */
    public static Admin getCurrenAdmin(){
        return (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
