package com.example.demo.model;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author: Jason
 * @Create: 2020/11/22  15:33
 * @Description redissonConnector连接类
 */

@Component
public class RedissonConnector {

    RedissonClient redissonClient;

    /**
     * 在构造之前就会调用这个，同时是只能初始化一次
     */
    @PostConstruct
    public void init(){
        redissonClient = Redisson.create();
    }

    public RedissonClient getRedissonClient(){
        return redissonClient;
    }
}
