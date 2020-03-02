package com.jason.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Jason
 * @Create: 2020/3/2  14:15
 * @Description 消息的生产者
 */
@RestController
public class Producer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping(value = "message")
    public  String  send(){
        kafkaTemplate.send("demo", "hello kafka");
        return "success";
    }
}
