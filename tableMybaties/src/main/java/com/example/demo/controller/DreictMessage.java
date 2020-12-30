package com.example.demo.controller;

import com.example.demo.redis.Producer;
import io.swagger.annotations.Api;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: Jason
 * @Create: 2020/12/12  20:12
 * @Description 发送直连的消息
 * 测试发送直连的消息
 */

@RestController
@Api
public class DreictMessage {

    @Autowired
    private RabbitTemplate  rabbitTemplate;


    @Autowired
    private Producer producer;

    /**
     * 测试发送消息-推模式
     * @return
     */
    @RequestMapping(value = "consumerSendMsg", method = RequestMethod.GET)
    public String pushSendMsg(){
        Message message = MessageBuilder.withBody("我们发送的消息内容存放在message的body里面".getBytes()).build();
        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        rabbitTemplate.setChannelTransacted(true);
        for(int i = 0; i < 10; i++){
            rabbitTemplate.convertAndSend("exchange", "testDirectRouting", "消息序号：" + i);
            rabbitTemplate.convertSendAndReceive("exchange", "testDirectRouting", "消息序号：" + i);
        }
        return "pull success";
    }


    /**
     * 测试rabbitmq中的推拉消费模式
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    @RequestMapping(value = "channelSendMsg", method = RequestMethod.GET)
    public String channelSend() throws IOException, TimeoutException {
        producer.producer();
        return "hello rabbitmq channel";
    }
}
