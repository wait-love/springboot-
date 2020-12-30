package com.example.demo.service.impl;

import com.example.demo.mapper.UserLogConfigMapper;
import com.example.demo.mapper.UserLogMapper;
import com.example.demo.model.UserLogConfig;
import com.example.demo.service.UserLogConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLogConfigServiceImpl implements UserLogConfigService {
    
    @Autowired
    private UserLogConfigMapper userLogConfigMapper;
    @Autowired
    private UserLogMapper userLogMapper;
    
    @Override
    public void save(UserLogConfig userLogConfig) {
    	// 插入
    	userLogConfigMapper.insertSelective(userLogConfig);
    	// 添加配置时，创建日志存储表
    	String tableName = userLogConfig.getTableName();
    	if(userLogMapper.existTable(tableName) > 0) {
    	    //这个设计是因为删除一个表时，受影响的行数为0，无法判断只能是这样设计
    		userLogMapper.dropTable(tableName);
    	}
    	userLogMapper.createTable(tableName);
    }
    
    @Override
    public List<UserLogConfig> findAll() {
        return userLogConfigMapper.selectAll();
    }
}