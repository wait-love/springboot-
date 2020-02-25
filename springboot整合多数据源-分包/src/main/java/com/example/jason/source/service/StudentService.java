package com.example.jason.source.service;

import com.example.jason.source.entity.Student;

/**
 * @Author: Jason
 * @Create: 2020/2/25  11:16
 * @Description 测试learn数据库
 */
public interface StudentService {

    /**
     * 插入学生类信息
     * @param student   实体类
     */
    void insert(Student student);
}
