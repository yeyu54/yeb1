package com.yeyu.service.impl;

import com.yeyu.pojo.Department;
import com.yeyu.mapper.DepartmentMapper;
import com.yeyu.pojo.RespBean;
import com.yeyu.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yeyu
 * @since 2021-11-14
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;


    /**
    * @Description:获取所有部门
    * @Param: []
    * @return
    * @date: 2022/2/7
    */
    @Override
    public List<Department> getAll(){
        return departmentMapper.getAll(-1);
    }
    /**
    * @Description: 添加部门
    * @Param:  dep
    * @return
    * @date: 2022/2/7
    */
    @Override
    public RespBean addDepart(Department dep){
        dep.setEnabled(true);
        departmentMapper.addDep(dep);
        if (1 == dep.getResult()){
            return RespBean.success("添加成功",dep);
        }
        return RespBean.error("添加失败");
    }
    /**
    * @Description: 删除部门
    * @Param: [id]
    * @return
    * @date: 2022/2/7
    */
    @Override
    public RespBean delDepart(Integer id){
        Department department = new Department();
        department.setId(id);
        departmentMapper.delDep(department);
        if (department.getResult() == -2){
            return RespBean.error("该部门下还有子部门，删除失败",department);
        }
        if (department.getResult() == -1){
            return RespBean.error("该部门下还有员工，删除失败");
        }
        if (department.getResult() == 1 ){
            return RespBean.error("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
