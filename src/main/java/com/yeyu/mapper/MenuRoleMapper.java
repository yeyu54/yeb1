package com.yeyu.mapper;

import com.yeyu.pojo.MenuRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface MenuRoleMapper extends BaseMapper<MenuRole> {
    /**
    * @Description:更新角色菜单
    * @Param:  rio, mids
    * @return
    * @date: 2022/1/21
    */
    Integer insertRecord(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
