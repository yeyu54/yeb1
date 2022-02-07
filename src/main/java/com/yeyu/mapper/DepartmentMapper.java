package com.yeyu.mapper;

import com.yeyu.pojo.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yeyu
 * @since 2021-11-14
 */
@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {
    /**
    * @Description: 获取所有部门
    * @Param: []
    * @return
    * @date: 2022/2/7
    */
    List<Department> getAll(Integer parentId);
    /**
    * @Description: 添加部门
    * @Param: [dep]
    * @return
    * @date: 2022/2/7
    */
    void addDep(Department dep);
    /**
    * @Description:删除部门
    * @Param: [dep]
    * @return
    * @date: 2022/2/7
    */
    void delDep(Department dep);
}
