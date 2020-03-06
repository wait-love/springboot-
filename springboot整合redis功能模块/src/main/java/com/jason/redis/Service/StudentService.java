package com.jason.redis.Service;

import com.jason.redis.pojo.Student;

/**
 * @Author: Jason
 * @Create: 2020/3/5  19:04
 * @Description 学生的Service层
 */
public interface StudentService {

    /**
     * 根据主键id来查询
     * @param id
     * @return
     */
    Student queryById(int id);
}
