package com.yeyu.service;

import com.yeyu.pojo.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yeyu.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yeyu
 * @since 2021-11-14
 */
public interface IDepartmentService extends IService<Department> {
    /**
    * @Description: 获取所有部门
    * @Param: []
    * @return
    * @date: 2022/2/7
    */
    List<Department> getAll();
    /**
    * @Description: 添加部门
    * @Param: []
    * @return
    * @date: 2022/2/7
    */
    RespBean addDepart(Department dep);

    RespBean delDepart(Integer id);
}
