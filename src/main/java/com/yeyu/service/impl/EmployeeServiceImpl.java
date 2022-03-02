package com.yeyu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yeyu.mapper.EmployeeMapper;
import com.yeyu.pojo.Employee;
import com.yeyu.pojo.RespPage;
import com.yeyu.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yeyu
 * @since 2021-11-14
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public RespPage getEmployeeBypage(Integer currenPage, Integer size, Employee employee, LocalDate[] beginDateScope){
        //开启分页
        Page<Employee> page = new Page<>(currenPage,size);
         IPage<Employee> p = employeeMapper.getEmployeeByPage(page,employee,beginDateScope);
        return new RespPage(p.getTotal(),p.getRecords());
    }
}
