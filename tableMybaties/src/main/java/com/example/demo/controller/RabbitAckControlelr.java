package com.example.demo.controller;

import io.swagger.annotations.Api;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @Create: 2020/12/4  21:54
 * @Description rabbitmq应答的controller
 */

@RestController
@Api
public class RabbitAckControlelr {

    @Autowired
    RabbitTemplate rabbitTemplate;


    /**
     * 凭空造一个交换机，但是路由键是可以存在的是就
     * 只会触发一个ConfirmCallBack这个方法
     * @return
     */
    @RequestMapping(value = "noexchange", method = RequestMethod.GET)
    public String noexchange(){
        //现在发送一个map的消息过去
        String messageId = UUID.randomUUID().toString();
        String messageData = "test topic message2, hello";
        String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", format);
        rabbitTemplate.convertAndSend("noExchange", "topic.women", map);
        return "ok";
    }


    /**
     * 这是对有交换机但是没有绑定的队列的测试
     * 测试结果显示它都会触发一个ConfirmCallBack与ReturnCallBack方法
     * 注意也只有在这种情况下，才会返回全部的数据,其余的情况都是不会返回数据的
     * @return
     */
    @RequestMapping(value = "noQueue", method = RequestMethod.GET)
    public String noQueue(){
        //现在发送一个map的消息过去
        String messageId = UUID.randomUUID().toString();
        String messageData = "test topic message2, hello";
        String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", format);
        rabbitTemplate.convertAndSend("noqueue", "noqueue", map);
        return "ok";
    }


    /**
     * 这是测试往交换机与队列中发送消息时，没有交换机与队列绑定的情况下
     * 测试结果显示，这种情况也只是会调用confirmCallBack方法
     * @return
     */
    @RequestMapping(value = "noThing", method = RequestMethod.GET)
    public String noThing(){
        //现在发送一个map的消息过去
        String messageId = UUID.randomUUID().toString();
        String messageData = "test topic message2, hello";
        String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", format);
        rabbitTemplate.convertAndSend("no", "noqueue", map);
        return "ok";
    }


    @RequestMapping(value = "success", method = RequestMethod.GET)
    public String success(){
        //现在发送一个map的消息过去
        String messageId = UUID.randomUUID().toString();
        String messageData = "test topic message2, hello";
        String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", format);
        rabbitTemplate.convertAndSend("topicExchange", "topic.women", map);
        return "success";
    }


}
