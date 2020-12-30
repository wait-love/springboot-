package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @Author: Jason
 * @Create: 2020/12/4  19:58
 * @Description topic模式下的配置类
 */

@SpringBootConfiguration
public class RabbitTopicConfig {

    public final static String man = "topic.man";

    public final static String woman = "topic.women";


    /**
     * 创建一个新的队列
     * @return
     */
    @Bean
    public Queue firstQueue(){
        return new Queue(RabbitTopicConfig.man);
    }


    /**
     * 创建另一个队列
     * @return
     */
    @Bean
    public Queue secondQueue(){
        return new Queue(RabbitTopicConfig.woman);
    }


    /**
     * 创建一个topic模式的交换机
     * @return
     */
    @Bean
    TopicExchange exchange(){
        return new TopicExchange("topicExchange");
    }


    /**
     * 队列与交换机之间进行绑定
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(){
        return BindingBuilder.bind(firstQueue()).to(exchange()).with(man);
    }


    /**
     * 将第二个队列与交换机进行绑定
     * @return
     */
    @Bean
    Binding bindingExchangeMessage2(){
        return BindingBuilder.bind(secondQueue()).to(exchange()).with("topic.#");
    }
}
