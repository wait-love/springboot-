package com.jason.redis.controller;

import com.jason.redis.Service.StudentService;
import com.jason.redis.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Jason
 * @Create: 2020/3/5  18:54
 * @Description redis测试类
 */
@RestController
public class RedisTest {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "hello_redis")
    public void redis(){
        Student student = studentService.queryById(1);
        System.out.println(student);
    }
}
