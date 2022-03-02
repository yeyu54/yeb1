package com.yeyu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页返回对象
 * @author 13474
 * @Package com.yeyu.pojo
 * @date 2022/3/216:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespPage {
    /*
    * 总条数
    * */
    private Long tolal;
    /*
    *数据list
    * */
    private List<?> date;
}
