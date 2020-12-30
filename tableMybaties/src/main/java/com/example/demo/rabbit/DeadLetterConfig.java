package com.example.demo.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Jason
 * @Create: 2020/12/6  16:06
 * @Description 死信队列中的配置类
 */

@SpringBootConfiguration
public class DeadLetterConfig {


    /**
     * 声明一个业务的交换机
     * @return
     */
    @Bean("businessExchange")
    public FanoutExchange businessExchange(){
        return new FanoutExchange("businessExcahnge");
    }


    /**
     * 声明一个死信交换机
     * @return
     */
    @Bean("deadLetterExchange")
    public FanoutExchange deadLetterExchange(){
        return new FanoutExchange("deadLetterExchange", false, false );
    }


    /**
     * 声明一个业务队列
     * @return
     */
    @Bean("businessQueueA")
    public Queue businessQueueA(){
        Map<String, Object> map = new HashMap(2);
        //队列绑定一个死信交换机属性
        map.put("x-dead-letter-exchange", "deadLetterExchange");
        //给队列中的死信路由键设置一个值
        map.put("x-dead-letter-routing-key", "deadLeeterA");
        //声明一个队列
        return new Queue("businessQueueA", true, false, false, map);
    }


    /**
     * 声明另外一个队列，不同于上面的队列
     * 他们之间的区别是，设置同意死信交换机
     * 但是设置不同的routyKey值
     * @return
     */
    @Bean("businessQueueB")
    public Queue businessQueueB(){
        Map<String, Object> map = new HashMap(2);
        //同上面那个队列一样，声明并且设置一些属性,绑定同一个死信交换机，设置不同的routyKey
        //队列绑定一个死信交换机属性，注意这是以个交换机名称
        map.put("x-dead-letter-exchange", "deadLetterExchange");
        //给队列中的死信路由键设置一个值
        //其实这个路由值是没有任何用的，因为是Fanout类型的交换机
        map.put("x-dead-letter-routing-key", "deadLeeterB");
        return new Queue("businessQueueB", true, false, false, map);
    }

    /**
     * 设置一个死信队列，但是它并没有其他的属性
     * @return
     */
    @Bean("deadLetterQueueA")
    public Queue deadLetterQueueA(){
        return new Queue("deadLetterQueueA", true, false, false);
    }

    /**
     * 设置好另外一个死信队列
     * @return
     */
    @Bean("deadLetterQueueB")
    public Queue deadLetterQueueB(){
        return new Queue("deadLetterQueueB", true, false,false);
    }


    /**
     * 绑定一个业务队列与业务交换机的绑定
     * 这里是只能设置一个routyKey值的
     * @return
     */
    @Bean("bindBusinessA")
    Binding bindbusinessQueueA(){
        return BindingBuilder.bind(businessQueueA()).to(businessExchange());
    }


    /**
     * 绑定queueB与交换
     * @return
     */
    @Bean("bindBusinessB")
    Binding bindBusinessQueueB(){
        return BindingBuilder.bind(businessQueueB()).to(businessExchange());
    }


    /**
     * 指定队列与Fanout交换机之间的绑定
     * @return
     */
    @Bean
    Binding bindingDeadWithExchange(){
        return BindingBuilder.bind(deadLetterQueueA()).to(deadLetterExchange());
    }


    /**
     * 将死信队列与死信交换机进行绑定
     * @return
     */
    @Bean
    Binding bindingDeadB(){
        return BindingBuilder.bind(deadLetterQueueB()).to(deadLetterExchange());
    }
}


