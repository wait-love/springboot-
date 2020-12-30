package com.example.demo.controller.redis;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Jason
 * @Create: 2020/12/15  21:06
 * @Description 学生信息的controller层
 */

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;


    /**
     * 查询出所有的学生信息并且将它存储进redis中去
     * @return
     */
    @RequestMapping(value = "queryAll", method = RequestMethod.GET)
    public String  query(){
        studentService.queryAll();
        return "查询出所有的学生信息";
    }
}
