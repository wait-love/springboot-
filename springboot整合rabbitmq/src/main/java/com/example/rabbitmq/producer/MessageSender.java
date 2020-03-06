package com.example.rabbitmq.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: Jason
 * @Create: 2020/3/1  12:49
 * @Description 消息生产者
 */
@Component
public class MessageSender {
    /**
     * AmqpTemplate 可以说是RabbitTempalte父类，RabbitTemplate实现类类RabbitOperate接口，RabbitOperation继承了AmqpTempalte接口
     */
    @Autowired
    private AmqpTemplate rabbitTempalte;


    @Autowired
    private RabbitTemplate rabbitTemplate1;

    /**
     * 单生产--》但消费
     */
    public void send(){
        for(int i =1; i < 10; i++){
            String sendMasg = "hello huqqing" + i + new Date();
            this.rabbitTemplate1.convertAndSend("helloQuery", sendMasg);
        }

    }
}
