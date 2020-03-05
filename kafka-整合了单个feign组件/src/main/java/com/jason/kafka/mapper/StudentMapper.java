package com.jason.kafka.mapper;

import com.jason.kafka.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {

    /**
     * 查询出所有的学生信息
     * @return
     */
    List<Student> selectAll();

}