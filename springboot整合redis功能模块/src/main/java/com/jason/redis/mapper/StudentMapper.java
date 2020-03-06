package com.jason.redis.mapper;

import com.jason.redis.pojo.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {

    /**
     * 查询出所有的学生信息
     * @return
     */
    List<Student> selectAll();


    /**
     * 根据用户的主键id来查询学生信息
     * @param id
     * @return
     */
    Student selectByPrimaryKey(@Param("id") int id);

}