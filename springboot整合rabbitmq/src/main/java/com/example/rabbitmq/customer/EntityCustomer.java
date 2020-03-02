package com.example.rabbitmq.customer;

import com.example.rabbitmq.pojo.Student;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Jason
 * @Create: 2020/3/1  14:21
 * @Description 实体的消费
 */
@Component
@RabbitListener(queues = "helloQuery")
public class EntityCustomer {
    @RabbitHandler
    public void process(Student student) {
        System.out.println("receive2 :" + student);
    }
}
