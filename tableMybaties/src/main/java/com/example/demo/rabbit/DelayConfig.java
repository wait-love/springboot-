package com.example.demo.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Jason
 * @Create: 2020/12/7  20:34
 * @Description 延迟队列配置类
 */

@SpringBootConfiguration
public class DelayConfig {

    //设置延迟的交换机
    public static final String DELAY_EXCAHNGE = "delay_exchange";

    //设置死信交换机
    public static final String DEAD_EXCHANGE = "dead_exchange";

    //设置好延迟队列
    public static final String DELAY_QUEUE = "delay_business_queue";

    //设置好死信队列
    public static final String DEAD_QUEUE = "deadLetterQueueC";

    //设置好延迟队列的路由键
    public static final String DELAY_ROUTY_KEY = "delayMessage";

    //设置好死信队列的路由键
    public static final String DEAD_ROUTY_KEY = "deadMessage";

    /**
     * 由于是在一个项目配置了多个queue导致bean的名称存在相同
     * 所以队列或者交换机的bean的命名不是很规范
     * 流程：与队列中的流程有很大的区别：
     * 它是通过对消息中的ttl属性进行了时间的限制，如果消息没被消费就会丢弃到
     * 队列设置好的死信交换机中去，最大的缺点就是：会受当前消息消费情况的影响，不消费，后面设置的消息也就永远不会
     * 被消费掉，可以用代码测试得出结论
     */


    /**
     * 声明一个延迟交换机
     * @return
     */
    @Bean("delayExchange1")
    public DirectExchange directExchange(){
        return new DirectExchange(DELAY_EXCAHNGE);
    }


    /**
     * 声明死信交换机
     * @return
     */
    @Bean("dead_Exchange2")
    public DirectExchange directDeadExchange(){
        return new DirectExchange(DEAD_EXCHANGE);
    }


    /**
     * 声明一个延迟队列并且设置好死信属性
     * @return
     */
    @Bean("delayQueue1")
    public Queue delayQueue(){
        Map<String, Object> map = new HashMap<>();
        //添加死信交换机
        map.put("x-dead-letter-exchange", "dead_exchange");
        //声明死信交换机的路由键
        map.put("x-dead-letter-routing-key", "dead_letter_message");
        return QueueBuilder.durable(DELAY_QUEUE).withArguments(map).build();
    }


    /**
     * 声明一个死信队列
     * @return
     */
    @Bean("deadLetterQueueC1")
    public Queue deadLetterQueueC(){
        return new Queue(DEAD_QUEUE);
    }


    /**
     * 绑定一个延迟队列与交换机
     * @return
     */
    @Bean
    public Binding delayBinding(){
        return BindingBuilder.bind(delayQueue()).to(directExchange()).with(DELAY_ROUTY_KEY);
    }


    /**
     * 绑定一个死信队列与死信交换机
     * @return
     */
    @Bean
    public Binding deadLetterBinding(){
        return BindingBuilder.bind(deadLetterQueueC()).to(directDeadExchange()).with("dead_letter_message");
    }
}
