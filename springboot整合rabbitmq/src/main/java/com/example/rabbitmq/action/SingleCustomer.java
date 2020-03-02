package com.example.rabbitmq.action;

import com.example.rabbitmq.producer.EntitySend;
import com.example.rabbitmq.producer.FanOutProducer;
import com.example.rabbitmq.producer.MessageSender;
import com.example.rabbitmq.producer.TopicProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Jason
 * @Create: 2020/3/1  14:04
 * @Description 单个消费者
 */
@RestController
public class SingleCustomer {

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private EntitySend entitySend;

    @Autowired
    private TopicProducer topicProducer;

    @Autowired
    private FanOutProducer fanOutProducer;

    @RequestMapping(value = "hello_message")
    public void  hello(){
//        messageSender.send();
        entitySend.send();
    }

    @RequestMapping(value = "hello_huqing")
    public void huqing(){
        topicProducer.send();
    }

    @RequestMapping(value = "hello_c")
    public void fanOut(){
        fanOutProducer.send();
    }
}
