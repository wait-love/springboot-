package com.example.demo.mapper;

import com.example.demo.model.UserLogConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserLogConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserLogConfig record);

    int insertSelective(UserLogConfig record);

    UserLogConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserLogConfig record);

    int updateByPrimaryKey(UserLogConfig record);
    
    public List<UserLogConfig> selectAll();
}