package com.jason.kafka.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Jason
 * @Create: 2020/3/2  16:24
 * @Description 消费者
 */
@Component
public class Consumer {

    @KafkaListener(topics = "demo")
    public void listen(ConsumerRecord record){
        System.out.println( "oio" +  record.topic() + record.offset()+ record.value());
    }
}
