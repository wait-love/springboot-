package com.example.jason.source.service.Impl;

import com.example.jason.source.entity.Student;
import com.example.jason.source.mapper.learn.StudentMapper;
import com.example.jason.source.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Jason
 * @Create: 2020/2/25  11:23
 * @Description 测试学生类
 */

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;


    @Override
    public void insert(Student student) {
        studentMapper.insert(student);
    }
}
