package com.example.rabbitmq.producer;

import com.example.rabbitmq.pojo.Student;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: Jason
 * @Create: 2020/3/1  14:17
 * @Description 实体的发送
 */
@Component
public class EntitySend {

    @Autowired
    private AmqpTemplate rabbitTempalte;


    @Autowired
    private RabbitTemplate rabbitTemplate1;

    public void send(){
            Student student = new Student(1,23, "佳盛",12,32);
            this.rabbitTemplate1.convertAndSend("helloQuery", student);
    }
}
