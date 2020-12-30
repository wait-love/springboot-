package com.example.demo.service.impl;

import com.example.demo.mapper.UserLogMapper;
import com.example.demo.model.UserLog;
import com.example.demo.service.UserLogService;
import com.example.demo.utils.PageRequest;
import com.example.demo.utils.PageResult;
import com.example.demo.utils.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLogServiceImpl implements UserLogService {
    
    @Autowired
    private UserLogMapper userLogMapper;
    
    @Override
    public void save(String tableName, UserLog userLog) {
    	// 插入
    	userLogMapper.insertSelective(tableName, userLog);
    }

    /**
     * 对分页结果进行了封装
     * @param pageRequest
     * @param tableName
     * @return
     */
    @Override
    public PageResult findAll(PageRequest pageRequest,String tableName) {
        return PageUtil.getPageResult(getPageInfo(pageRequest,tableName));
    }


    /**
     * 这里是进行了分页操作
     * @param pageRequest
     * @param tableName
     * @return
     */
    private PageInfo<UserLog> getPageInfo(PageRequest pageRequest, String tableName){
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<UserLog> userLogs = userLogMapper.selectAll(tableName);
        return new PageInfo<UserLog>(userLogs);

    }
}