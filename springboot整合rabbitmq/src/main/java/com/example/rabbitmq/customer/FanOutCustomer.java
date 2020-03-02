package com.example.rabbitmq.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Jason
 * @Create: 2020/3/1  14:58
 * @Description 广播模式的消费者
 */
@Component
@RabbitListener(queues = "fanout_C")
public class FanOutCustomer {

    @RabbitHandler
    public void receive(String str){
        System.out.println("receive" + str);
    }
}
