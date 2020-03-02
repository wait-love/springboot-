package com.example.rabbitmq.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Jason
 * @Create: 2020/3/1  12:55
 * @Description 单消费者
 */
@Component
@RabbitListener(queues = "helloQuery")
public class SingleCustomerAc {

    @RabbitHandler
    public void process(String hello){
        System.out.println("receive1 :" + hello);
    }
}
