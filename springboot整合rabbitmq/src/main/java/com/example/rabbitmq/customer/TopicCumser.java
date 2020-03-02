package com.example.rabbitmq.customer;

import com.example.rabbitmq.pojo.Student;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Jason
 * @Create: 2020/3/1  14:48
 * @Description topic模式下的消费者
 */
@Component
@RabbitListener(queues = "topic.message")
public class TopicCumser {

    @RabbitHandler
    public void process(String  student){
        System.out.println("receive:" + student);
    }
}
