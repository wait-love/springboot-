package com.example.demo.redis;

import com.alibaba.fastjson.JSON;
import com.example.demo.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: Jason
 * @Create: 2020/11/30  19:49
 * @Description 消息发送类
 */

@Component
@Slf4j
public class MessageSend {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 消息发送的方法所在
     * @param message
     */
    public void messageSend(Message message){
        //将对象进行序列化
        String messageJson = JSON.toJSONString(message);
        log.info("发送的消息体中的信息为:{}", messageJson);

        //这里是采用了发布与订阅的模式来发送消息
        stringRedisTemplate.convertAndSend("chat", messageJson);
    }
}
