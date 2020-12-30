package com.example.demo.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Jason
 * @Create: 2020/12/9  21:45
 * @Description 基于插件实现延迟队列
 */

@SpringBootConfiguration
public class PlugesDelayConfig {

    //队列名称
    public static final String DELAYED_QUEUE_QUEUE_NAME = "delay.queue.demo.delay.queue";

    //延迟交换机的名称
    public static final String DELAYED_EXCHANGE_NAME = "delay.queue.demo.delay.exchange";

    //延迟队列与交换机的路由键
    public static final String DELAY_ROUTYING_KEY = "delay.queue.demo.delay.routykey";


    /**
     * 定义一个延迟队列的名称
     * @return
     */
    @Bean
    public Queue immediateQueue(){
        return new Queue(DELAYED_QUEUE_QUEUE_NAME);
    }


    /**
     * 交换机
     * @return
     */
    @Bean
    public CustomExchange customExchange(){
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(DELAYED_EXCHANGE_NAME, "x-delayed-message", true, false, args);
    }


    /**
     * 将队列与交换机通过路由键进行绑定
     * @return
     */
    @Bean
    public Binding bindingNotify(){
        return BindingBuilder.bind(immediateQueue()).to(customExchange()).with(DELAY_ROUTYING_KEY).noargs();
    }
}
