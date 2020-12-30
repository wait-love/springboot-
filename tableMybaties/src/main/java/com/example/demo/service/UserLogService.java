package com.example.demo.service;

import com.example.demo.model.UserLog;
import com.example.demo.utils.PageRequest;
import com.example.demo.utils.PageResult;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserLogService {

    /**
     * 保存用户日志
     * @return
     */
    void save(String tableName, UserLog userLog);
    
    /**
     * 查找全部用户日志
     * @return
     */
    PageResult findAll(PageRequest pageRequest ,String tableName);




}