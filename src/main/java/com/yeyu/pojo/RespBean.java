package com.yeyu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 13474
 * @Package com.yeyu.pojo
 * @date 2021/11/1413:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {
    private long code;
    private String message;
    private Object obj;

    /**
    * Description:返回成功结果
    *
    * @param message
    *
    * @retrun
    * */
    public static RespBean success(String message){
        return new RespBean(200,message,null);
    }
    /**
    * @Description:返回成功结果
    * @param message
    * @param obj
    * @retrun
    * */
    public static RespBean success(String message,Object obj){
        return new RespBean(200,message,obj);
    }
    /**
    * @Param: [message]
    * @return
    * @date: 2021/11/14
    */
    public static RespBean error(String message){
        return new RespBean(500,message,null);
    }
    /**
    * @Param: [message, obj]
    * @return
    * @date: 2021/11/14
    */
    public static RespBean error(String message,Object obj){
        return new RespBean(500,message,obj);
    }
}
