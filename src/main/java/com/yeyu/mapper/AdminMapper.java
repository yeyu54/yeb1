package com.yeyu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yeyu.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yeyu
 * @since 2021-11-14
 */
@Repository
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    /**
    * @Description:获取所有操作员
    * @Param: [id, keywords]
    * @return
    * @date: 2022/2/7
    */
    List<Admin> getAll(Integer id, String keywords);
}
