package com.yeyu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yeyu.pojo.AdminRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yeyu
 * @since 2021-11-14
 */
@Mapper
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

   Integer addAdminRole(@Param("adminid") Integer adminid, @Param("rids") Integer[] rids);
}
