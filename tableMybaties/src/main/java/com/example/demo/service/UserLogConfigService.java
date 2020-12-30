package com.example.demo.service;

import com.example.demo.model.UserLogConfig;

import java.util.List;

public interface UserLogConfigService {

    /**
     * 保存用户日志配置
     * @return
     */
    void save(UserLogConfig userLogConfig);
    
    /**
     * 查找全部用户日志配置
     * @return
     */
    List<UserLogConfig> findAll();

}