package com.jason.feign.controller;

import com.jason.feign.FeignTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Jason
 * @Create: 2020/3/4  17:50
 * @Description feign的测试
 */
@RestController
public class FeignTest2 {

    @Autowired
    private FeignTest feignTest;

    @RequestMapping(value = "hello")
    public void test(){
        feignTest.send2();
    }
}
