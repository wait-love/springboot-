package com.example.jason.source.mapper.loex;

import com.example.jason.source.entity.Score;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreMapper{

    int deleteByPrimaryKey(Integer id);

    int insert(Score record);

    Score selectByPrimaryKey(Integer id);

    List<Score> selectAll();

    int updateByPrimaryKey(Score record);
}