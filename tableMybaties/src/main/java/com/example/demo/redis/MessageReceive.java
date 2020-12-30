package com.example.demo.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: Jason
 * @Create: 2020/11/30  19:56
 * @Description 信息处理类
 */

@Slf4j
@Component
public class MessageReceive {


    /**
     * 接收消息並且将他转换为一个Map类型
     * @param message
     * @return
     */
    public void receive(String message){
        Map<String, String> map = JSON.parseObject(message, Map.class);
        log.info("获取到的信息为:{}", map);
        String sessionId = map.get("sessionId");
        String message1 = map.get("message");
        log.info("其中会话id为：{}, 消息数据为:{}", sessionId, message1);
    }
}
