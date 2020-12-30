package com.example.demo.model;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

/**
 * @Author: Jason
 * @Create: 2020/12/8  21:21
 * @Description 通过rabbit来发送消息
 */
public class MyMessagePostProcessor  implements MessagePostProcessor {

    private  Integer ttl;

    public MyMessagePostProcessor(Integer ttl){
        this.ttl = ttl;
    }

    /**
     * 给消息设置ttl时间
     * @param message
     * @return
     * @throws AmqpException
     */
    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        message.getMessageProperties().getHeaders().put("expiration", ttl.toString());
        return message;
    }
}
