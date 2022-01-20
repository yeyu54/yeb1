package com.yeyu.mapper;

import com.yeyu.pojo.Menu;
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
public interface MenuMapper extends BaseMapper<Menu> {


    List<Menu> getmenuid(Integer id);

    List<Menu> getmenuwithrole();

    List<Menu> getAllMenus();
}
