package com.example.rabbitmq.producer;

import com.example.rabbitmq.pojo.Student;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Jason
 * @Create: 2020/3/1  14:40
 * @Description topic模式 -通配符
 */
@Component
public class TopicProducer {

    @Autowired
    private AmqpTemplate rabbitTempalte;

    @Autowired
    private RabbitTemplate rabbitTemplate1;

    public void send(){
        Student student = new Student(1,23, "胡清",12,32);
        //参数解析 第一个参数就是交换机 ，第二个就是routingkey,第三个就是信息
        this.rabbitTemplate1.convertAndSend("exchange", "topic.message", student.getStudentName());
    }
}

