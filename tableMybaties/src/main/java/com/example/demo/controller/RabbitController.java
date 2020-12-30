package com.example.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: Jason
 * @Create: 2020/12/2  20:38
 * @Description rabbitmq的controller层
 */

@RestController
@Api(produces = "rabbitmq发送消息的地方")
public class RabbitController {

    //这里直接使用rabbitmq中的模板类来发送消息的
    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "sendMessage", method = RequestMethod.GET)
    public String sendDirectMessage(){
        //现在发送一个map的消息过去
        String messageId = UUID.randomUUID().toString();
        String messageData = "test message, hello";
        //将lcoaldateTime转换为string类型的
        String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", format);
        rabbitTemplate.convertAndSend("exchange", "testDirectRouting", map, new MessagePostProcessor() {

            //这里给消息设置优先级
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {

                //设置消息的优先级别为最大的
                message.getMessageProperties().setPriority(10);
                return message;
            }
        });
        return "ok";
    }


    @RequestMapping(value = "sendMessageTopic", method = RequestMethod.GET)
    public String sendTopicMessage(){
        //现在发送一个map的消息过去
        String messageId = UUID.randomUUID().toString();
        String messageData = "test topic message, hello";
        String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", format);
        rabbitTemplate.convertAndSend("topicExchange", "topic.man", map);
        return "o";
    }

    @RequestMapping(value = "sendMessageTopic2", method = RequestMethod.GET)
    public String sendTopicMessage2(){
        //现在发送一个map的消息过去
        String messageId = UUID.randomUUID().toString();
        String messageData = "test topic message2, hello";
        String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", format);
        rabbitTemplate.convertAndSend("topicExchange", "topic.women", map);
        return "ok";
    }


    @RequestMapping(value = "sendFanoutMessage", method = RequestMethod.GET)
    public String sendFanout(){
        //现在发送一个map的消息过去
        String messageId = UUID.randomUUID().toString();
        String messageData = "test topic message2, hello";
        String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", format);
        rabbitTemplate.convertAndSend("fanoutExchange", null, map);
        return "ok";
    }
}
