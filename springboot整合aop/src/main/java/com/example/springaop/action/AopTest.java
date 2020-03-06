package com.example.springaop.action;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Jason
 * @Create: 2020/2/26  18:26
 * @Description
 */
@RestController
public class AopTest {

    @RequestMapping(value = "hello")
    public String test(){
        return "hello world";
    }
}
