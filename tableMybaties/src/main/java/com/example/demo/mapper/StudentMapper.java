package com.example.demo.mapper;

import com.example.demo.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {

    /**
     * 对数据进行新增操作
     * @param record
     * @return
     */
    int insert(Student record);

    /**
     * 查询出所有的数据无需排序
     * @return
     */
    List<Student> selectAll();

    /**
     * 验证redis中分页功能按照id排序
     * @return
     */
    List<Student> queryDatas();
}