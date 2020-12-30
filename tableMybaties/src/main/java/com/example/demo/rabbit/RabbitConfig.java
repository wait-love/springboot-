package com.example.demo.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Jason
 * @Create: 2020/12/4  21:37
 * @Description
 */

@SpringBootConfiguration
@Slf4j
public class RabbitConfig {


    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        //rabbitmq注入一个连接工厂
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //设置为强制性的，不关是否发送成功都需要确认
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {

            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
               //这里主要是对confirmCallback方法的调用
                log.info("confirmCallback: 中的相关数据为:{}", correlationData);
                log.info("调用方法之后的确认情况为:{}", b);
                log.info("调用方法失败时的原因为:{}", s);
            }
        });

        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                log.info("returnCallBack中的消息为:{}", returnedMessage);
            }
        });
        return  rabbitTemplate;
    }

    /**
     * 这两个方法其实就是对于rabbitmq服务的响应
     * 1：消息推送到server 但在server中找不到交换机  这样就只会触发confirm方法
     * 2：消息推送到server 但在server中找不到队列
     * 3：消息推送到server,但在server中找不到交换机与队列
     * 4：消息推送成功
     */


    /**
     * 测试一个没有队列的交换机
     * @return
     */
    @Bean
    DirectExchange create(){
        return new DirectExchange("noqueue");
    }


    /**
     * 直连的队列名称
     * @return
     */
    @Bean
    public Queue testDirectQueue(){
        /**
         * dureable:是否持久化，默认是false， 持久化队列： 会被存储在磁盘上，当消息代理重启时仍然存在
         * exclusive: 默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列会被关闭
         * autoDelete:是否自动删除，当没有生产者或者消费者使用队列，该队列会自动删除
         * return new Queue("testDirectQueue", true, true, fasle,)
         */
        Map<String, Object> map = new HashMap<>();
        //在这里配置好队列的优先级0-10  默认的是5 注意这里Map中的key需要注意下，是否正确的
        map.put("x-max-priority", 10);
        return new  Queue("firstQueue", true, false,false,map);
    }


    /**
     * 配置好第二个队列
     * 跟优先级别最大的比较
     * @return
     */
    public Queue secondQueue(){
          return new Queue("secondQueue", true);
    }


    /**
     * 直连交换机
     * @return
     */
    @Bean
    DirectExchange testDirectExchange(){
        return new DirectExchange("exchange", true, false);
    }

    /**
     * 指定直连的队列与交换机，并且指定routyKey为：testDirectRouting
     * @return
     */
    @Bean
    Binding bindingDirect(){
        return BindingBuilder.bind(testDirectQueue()).to(testDirectExchange()).with("testDirectRouting");
    }


}
