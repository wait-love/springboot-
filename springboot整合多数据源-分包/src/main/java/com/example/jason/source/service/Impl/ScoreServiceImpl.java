package com.example.jason.source.service.Impl;

import com.example.jason.source.entity.Score;
import com.example.jason.source.mapper.loex.ScoreMapper;
import com.example.jason.source.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Jason
 * @Create: 2020/2/25  11:20
 * @Description 学生的实现类
 */
@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    public  ScoreMapper scoreMapper;

    @Override
    public List<Score> queyAll() {
        return scoreMapper.selectAll();
    }
}
