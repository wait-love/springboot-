package com.example.demo.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.awt.image.DirectColorModel;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Jason
 * @Create: 2020/12/8  19:32
 * @Description ttl延迟队列配置类
 */

@SpringBootConfiguration
public class TtlDelayConfig {

    public static final String TTL_DELAY_EXCHANGE = "ttlDelayExchange";

    public static final String DEAD_LETTER_EXCHANGE = "ttlDeadExchange";

    public static final String TTL_ROUTY_KEY = "ttlRoutyKeyA";

    public static final String TTL_ROUTY_KEY_B = "ttlRoutyKeyB";

    public static final String TTL_DELAY_QUEUE = "ttlDelayQueu";

    public static final String TTL_DELAY_QUEUEB = "ttlDelayQue2";

    public static final String DEAD_LETTER_QUEUE = "deadLetterQueue";

    public static final String DEAD_LETTER_QUEUEB = "deadLetterQueueB";

    public static final String ROUTY_KEY_QUEUE_EXCHANGE = "routyKeyA";

    public static final String ROUTY_KEY_QUEUE_EXCHANGEB = "routyKeyB";


    /**
     * 因为在同一个模块中命名了多个队列以及交换机，存在很多bean的名称相同的情况
     * 所以对于bean的名称不是很规范
     * 流程就是往延迟对列中发送消息，同时对队列中死信路由属性，以及交换机，ttl属性设置好对应的时间
     * 只要队列中的消息在规定时间内没有被消费掉，那么就会变成死信，只需监听死信队列中对队列中的消息进行消费
     *
     */


    /**
     * 声明一个延迟队列
     * @return
     */
    @Bean("deadLetterQueueB05")
    public DirectExchange directExchange(){
        return new DirectExchange(TTL_DELAY_EXCHANGE);
    }


    /**
     * 声明一个死信交换机
     * @return
     */
    @Bean("deadLetterQueueB03")
    public DirectExchange deadLetterExchange(){
        return new DirectExchange(DEAD_LETTER_EXCHANGE);
    }

    /**
     * 定义好一个超时的队列
     * @return
     */
    @Bean("deadLetterQueueB01")
    public Queue delayQueueA(){
        Map<String, Object> map = new HashMap<>();
        //设置好死信的交换机
        map.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        //设置好死信与队列的路由键
        map.put("x-dead-letter-routing-key", TTL_ROUTY_KEY);
        //设置队列的ttl时间-设置好10秒
        map.put("x-message-ttl", 1000);
        return QueueBuilder.durable(TTL_DELAY_QUEUE).withArguments(map).build();
    }


    /**
     * 定义好一个超时时间为6秒的队列
     * @return
     */
    @Bean("deadLetterQueue09")
    public Queue delayQueueB(){
        Map<String, Object> map = new HashMap<>();
        //设置好死信的交换机
        map.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        //设置好死信与队列的路由键
        map.put("x-dead-letter-routing-key", TTL_ROUTY_KEY_B);
        //设置队列的ttl时间-设置好10秒
        map.put("x-message-ttl", 6000);
        return QueueBuilder.durable(TTL_DELAY_QUEUEB).withArguments(map).build();
    }


    /**
     * 声明一个队列用来处理超时10秒的死信
     * @return
     */
    @Bean("dQueue09")
    public Queue deadLetterQueueA(){
        return new Queue(DEAD_LETTER_QUEUE);
    }


    /**
     * 声明一个队列用来处理超时6秒的死信
     * @return
     */
    @Bean("eue09")
    public Queue deadLetterQueueB(){
        return new Queue(DEAD_LETTER_QUEUEB);
    }


    /**
     * 声明延迟队列与交换机的绑定
     * @return
     */
    @Bean("deadL09")
    public Binding delayBindA(){
        return BindingBuilder.bind(delayQueueA()).to(directExchange()).with(ROUTY_KEY_QUEUE_EXCHANGE);
    }


    /**
     * 声明延迟队列与交换机的绑定
     * @return
     */
    @Bean("de9")
    public Binding delayBindB(){
        return BindingBuilder.bind(delayQueueB()).to(directExchange()).with(ROUTY_KEY_QUEUE_EXCHANGEB);
    }


    /**
     * 声明死信队列与交换机的绑定
     * @return
     */
    @Bean
    public Binding deadBindA(){
        return BindingBuilder.bind(deadLetterQueueA()).to(deadLetterExchange()).with(TTL_ROUTY_KEY);
    }


    /**
     * 声明死信队列与交换机的绑定
     * @return
     */
    @Bean
    public Binding deadBindB(){
        return BindingBuilder.bind(deadLetterQueueB()).to(deadLetterExchange()).with(TTL_ROUTY_KEY_B);
    }
}
