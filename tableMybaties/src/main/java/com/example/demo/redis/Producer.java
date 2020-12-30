package com.example.demo.redis;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: Jason
 * @Create: 2020/12/14  19:38
 * @Description 生产者
 */

@Component
public class Producer {

    public  void producer() throws IOException, TimeoutException {
        //创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        //获取Connection
        Connection connection = connectionFactory.newConnection();

        //通过Connection创建一个新的channel
        Channel channel = connection.createChannel();

        //指定我们的消息投递模式，消息的确认模式
        channel.confirmSelect();
        String exchangeName = "test_confirm_exchange";
        String routyKey = "confirm.save";

        //5 发送一条消息
        String msg = "Hello RabbitMQ Send confirm message";
        channel.basicPublish(exchangeName, routyKey, null, msg.getBytes());

        //添加一个确认监听
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long l, boolean b) throws IOException {
                System.out.println("----------no ack----------");
            }

            @Override
            public void handleNack(long l, boolean b) throws IOException {
                System.out.println("--------ack!-------");
            }
        });
    }
}
