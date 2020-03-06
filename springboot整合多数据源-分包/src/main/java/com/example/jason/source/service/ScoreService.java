package com.example.jason.source.service;

import com.example.jason.source.entity.Score;

import java.util.List;

/**
 * @Author: Jason
 * @Create: 2020/2/25  11:18
 * @Description 测试loex数据库的信息
 */
public interface ScoreService {

    /**
     * 查询出全部的分数
     * @return   返回全的分数
     */
    List<Score> queyAll();
}
