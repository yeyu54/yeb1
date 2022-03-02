package com.yeyu.service;

import com.yeyu.pojo.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yeyu.pojo.RespPage;

import java.time.LocalDate;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yeyu
 * @since 2021-11-14
 */
public interface IEmployeeService extends IService<Employee> {
    /**
    * @Description:获取所有员工分页
    * @Param: [currenpage, size, employee, begiDateScope]
    * @return
    * @date: 2022/3/2
    */
    RespPage getEmployeeBypage(Integer currenPage, Integer size, Employee employee, LocalDate[] beginDateScope);
}
