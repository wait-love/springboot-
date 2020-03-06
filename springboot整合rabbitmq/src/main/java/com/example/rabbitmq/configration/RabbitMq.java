package com.example.rabbitmq.configration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Jason
 * @Create: 2020/3/1  9:14
 * @Description rabbitmq的配置类
 *
 */
@Configuration
@Slf4j
public class RabbitMq {

    @Autowired
    private CachingConnectionFactory connectionFactory;

    //定义好队列的名称
    final static String queueName = "helloQuery";

    //注入到ioc容器中去
    @Bean
    public Queue helloQueue(){
        return new Queue(queueName);
    }

    @Bean
    public Queue userQueue(){
        return new Queue("user");
    }

    @Bean("dirQueue")
    public Queue directQueue(){
        return new Queue("direct");
    }

    @Bean(name = "message")
    public Queue queueMessage(){
        return new Queue("topic.message");
    }

    @Bean(name = "AMessage")
    public Queue  AMessage(){
        return new Queue("fanout_A");
    }


    @Bean(name = "BMessage")
    public  Queue BMessage(){
        return new  Queue("fanout_B");
    }

    @Bean(name = "CMessage")
    public Queue CMessage(){
        return new Queue("fanout_C");
    }

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange("directExchange");
    }

    @Bean
    TopicExchange  exchange(){
        return new TopicExchange("exchange");
    }


    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    /**
     *  自动装填将name为dirQueue赋值给Queue dirQueue
     */
    @Bean
    Binding bindingExchangeDirect(@Qualifier("dirQueue") Queue dirQueue, DirectExchange directExchange){
        return BindingBuilder.bind(dirQueue).to(directExchange).with("direct");
    }

    @Bean
    Binding bindingExchangeMessage(@Qualifier("message") Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with("topic.#");
    }

    /**
     * 再次绑定队列与交换机的routeKey
     */
    @Bean
    Binding bindingExchangeMessge(@Qualifier("AMessage") Queue queue, FanoutExchange  fanoutExchange){
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeMessgeB(@Qualifier("BMessage") Queue queue, FanoutExchange  fanoutExchange){
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeMessgeC(@Qualifier("CMessage") Queue queue, FanoutExchange  fanoutExchange){
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    @Bean
    public RabbitTemplate  rabbitTemplate(){
        //若使用confirm-callback或者是return-callback，必须要配置publishConfirms为true
        //每个rabbitTemplate只能有一个confirm-callback
        //使用return-callback时必须设置maddtory为true，或者在配置中设置madatory-expression的值为true
        connectionFactory.setPublisherReturns(true);
        connectionFactory.setPublisherConfirms(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        /**
         * 如果消息没有到达exchange。则confirm回调。ack=false
         * 如果消息到达exchange，则confirm回调，ack=true
         * exchange到queue成功，就不return
         * excahnge到queue失败，则回调return，则回调return(需设置mandatory=true,否则不会回调，消息就会丢失)
         */
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                if(b){
                    log.info("消息发送成功："+ correlationData);
                }else{
                    log.info("消息发送失败" + correlationData);
                }

            }
        });
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                log.info("消息丢失" );
            }
        });
        return rabbitTemplate;
    }
}
