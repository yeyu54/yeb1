package com.yeyu.exception;

import com.yeyu.pojo.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author 13474
 * @Package com.yeyu.exception
 * @date 2022/1/1716:27
 */
@RestControllerAdvice
public class GlobalException {
 @ExceptionHandler(SQLException.class)
    public RespBean mysqlException(SQLException e){
     if (e instanceof SQLIntegrityConstraintViolationException){
         return RespBean.error("该数据有关联，操作失败");
     }
    return RespBean.error("操作失败");
 }
}
