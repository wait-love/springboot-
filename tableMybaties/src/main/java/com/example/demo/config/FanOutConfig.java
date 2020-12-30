package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;


/**
 * @Author: Jason
 * @Create: 2020/12/4  20:39
 * @Description fanout配置类
 */

@SpringBootConfiguration
public class FanOutConfig {

    /**
     * 第一个队列
     * @return
     */
    @Bean
    public Queue queueA(){
        return new Queue("fanout.a");
    }


    /**
     * 创建第二个队列
     * @return
     */
    @Bean
    public Queue queueB(){
        return new Queue("fanout.B");
    }


    /**
     * 创建第三个队列
     * @return
     */
    @Bean
    public Queue queueC(){
        return new Queue("fanout.C");
    }


    /**
     * fanout类型的交换机
     * @return
     */
    @Bean
    FanoutExchange  fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }


    /**
     * 将队列与交换进行绑定
     * @return
     */
    @Bean
    Binding bindingExchange(){
        return BindingBuilder.bind(queueA()).to(fanoutExchange());
    }


    @Bean
    Binding bindingExchangeB(){
        return BindingBuilder.bind(queueB()).to(fanoutExchange());
    }

    @Bean
    Binding bindingExchangeC(){
        return BindingBuilder.bind(queueC()).to(fanoutExchange());
    }


}
