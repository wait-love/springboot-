package com.example.demo.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.demo.rabbit.DelayConfig.DELAY_EXCAHNGE;
import static com.example.demo.rabbit.DelayConfig.DELAY_ROUTY_KEY;
import static com.example.demo.rabbit.PlugesDelayConfig.DELAYED_EXCHANGE_NAME;
import static com.example.demo.rabbit.PlugesDelayConfig.DELAY_ROUTYING_KEY;
import static com.example.demo.rabbit.TtlDelayConfig.*;

/**
 * @Author: Jason
 * @Create: 2020/12/7  21:14
 * @Description 发送消息的地方
 */

@Component
public class SendMsg {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(Object obj, Integer type){
        switch (type){
            case 10:
                rabbitTemplate.convertAndSend(TTL_DELAY_EXCHANGE, ROUTY_KEY_QUEUE_EXCHANGE, obj );
                break;
            case 6:
                rabbitTemplate.convertAndSend(TTL_DELAY_EXCHANGE, ROUTY_KEY_QUEUE_EXCHANGEB, obj);
                break;

        }
    }

    /**
     * 给消息设置好ttl时间设置
     * @param obj
     * @param type
     */
    public  void delayMsg(String obj, Integer type){
        //给消息设定好超时限制
//        MessageProperties messageProperties = new MessageProperties();
//        messageProperties.setExpiration(type.toString()); // 设置过期时间，单位：毫秒
//        byte[] msgBytes = obj.getBytes();
//        Message message = new Message(msgBytes, messageProperties);
        rabbitTemplate.convertAndSend(DELAYED_EXCHANGE_NAME, DELAY_ROUTYING_KEY,  obj, message -> {
            // 注意这里时间可以使long，而且是设置header
            message.getMessageProperties().setHeader("x-delay",type);
            return message;
        });
    }
}
