package com.yeyu.controller;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 13474
 * @Package com.yeyu.controller
 * @date 2021/12/522:09
 */
public class hellocontroller {
    @GetMapping("hello")
    public String hello(){
        return "hello";
    }
}
