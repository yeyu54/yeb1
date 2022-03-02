package com.yeyu.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeyu.pojo.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yeyu
 * @since 2021-11-14
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
    /**
    * @Description:获取所有员工分页
    * @Param: [page, employee, begiDateScope]
    * @return
    * @date: 2022/3/2
    */
    IPage<Employee> getEmployeeByPage(Page<Employee> page, @Param("employee") Employee employee,
                            @Param("beginDateScope") LocalDate[] beginDateScope);
}
