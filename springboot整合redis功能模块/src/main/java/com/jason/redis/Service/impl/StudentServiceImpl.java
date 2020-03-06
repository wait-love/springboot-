package com.jason.redis.Service.impl;

import com.jason.redis.Service.StudentService;
import com.jason.redis.mapper.StudentMapper;
import com.jason.redis.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Jason
 * @Create: 2020/3/5  19:08
 * @Description 学生的实现类
 */

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private RedisTemplate redisTemplate;
    
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 首先就是在redis查询，查询过后如果没有，然后就是往数据库中查询
     * 在数据库查询时，我们会把数据更新到缓存中去
     * @param id
     * @return
     */
    @Override
    public Student queryById(int id) {
        //首先定义一个属性
        String key = "user" + id;
        Boolean result = redisTemplate.hasKey(key);
        ValueOperations<String, Student> valueOperations = redisTemplate.opsForValue();
        //如果在redis中存在这样一个key值，那么就会直接从缓存中去取
        if (result){
            Student student = valueOperations.get(key);
            return student;
        }else {
            Student student = studentMapper.selectByPrimaryKey(id);

            /**
             * 将数据写入缓存中去
             * 并且将一个数据设置为5个小时的失效时间
             */
            valueOperations.set(key, student,5, TimeUnit.HOURS);
        }
        return null;
    }
}
