package com.example.demo.controller;

import com.example.demo.rabbit.SendMsg;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

/**
 * @Author: Jason
 * @Create: 2020/12/6  19:59
 * @Description 死信controller层
 */

@RestController
@Api(produces = "死信队列测试的controller")
@Slf4j
public class DeadLetterController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private SendMsg sendMsg;

    @RequestMapping(value = "sendDeadMessge", method = RequestMethod.GET)
    public String sendDeadMessage(){
        rabbitTemplate.convertAndSend("businessExcahnge", "", "deadletter");
        return "dead";
    }


    @RequestMapping(value = "ttl", method = RequestMethod.GET)
    public String send(String msg, Integer type){
        log.info("当前时间为:{}," , LocalDateTime.now());
        sendMsg.sendMsg("hello ttl", type);
        return "ttl";
    }

    @RequestMapping(value = "feature", method = RequestMethod.GET)
    public String sendOneMessage(String msg, Integer type){
        log.info("当前时间为:{}," , LocalDateTime.now());
        sendMsg.delayMsg("hello everyone", type);
        return "one ttl";
    }


    @RequestMapping(value = "plugSend", method = RequestMethod.GET)
    public String plugSend(String msg, Integer type){
        log.info("当前时间为456986776767:{}," , LocalDateTime.now());
        sendMsg.delayMsg("hello pluges", type);
        return "hello pluges";
    }
}
