package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import com.example.demo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Jason
 * @Create: 2020/12/15  21:04
 * @Description 对学生信息的数据进行缓存操作
 */

@Service
public class StudentServiceImpl implements StudentService {

    //设定一个学生全部信息的key值
    private static final String ALLSTUDENTKEY = "student_keys";

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private RedisUtil redisUtil;


    /**
     * 利用一个map集合来封装一个list
     */
    @Override
    public void queryAll() {
        List<Student> students = studentMapper.selectAll();
        //在这里将学生信息中的所有信息存储进redis中去
        redisUtil.lSet(ALLSTUDENTKEY, JSONObject.toJSON(students), 10000);
    }
}
