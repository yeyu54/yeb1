package com.yeyu.mapper;

import com.yeyu.pojo.Role;
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
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getroles(Integer adminId);
}
