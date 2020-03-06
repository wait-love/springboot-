package com.example.rabbitmq.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Jason
 * @Create: 2020/3/1  14:09
 * @Description 单个生产者多个消费者
 */
@Component
@RabbitListener(queues = "helloQuery")
public class MoreCutomer {

    @RabbitHandler
    public void process(String hello){
        System.out.println("receive2 :" + hello);
    }
}
