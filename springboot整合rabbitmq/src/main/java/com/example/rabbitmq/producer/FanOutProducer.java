package com.example.rabbitmq.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Jason
 * @Create: 2020/3/1  14:55
 * @Description 广播模式
 */
@Component
public class FanOutProducer {
    @Autowired
    private AmqpTemplate rabbitTempalte;


    @Autowired
    private RabbitTemplate rabbitTemplate1;

    public void send(){
        rabbitTemplate1.convertAndSend("fanoutExchange", "wqer", "hello 清菇凉");
    }
}
