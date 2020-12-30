//package com.example.demo.config;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.boot.SpringBootConfiguration;
//import org.springframework.context.annotation.Bean;
//
//
///**
// * @Author: Jason
// * @Create: 2020/12/2  20:23
// * @Description rabbit直连的配置类
// */
//
//@SpringBootConfiguration
//public class RabbitDirectConfig {
//
//
//    /**
//     * 直连的队列名称
//     * @return
//     */
//    @Bean
//    public Queue testDirectQueue(){
//        /**
//         * dureable:是否持久化，默认是false， 持久化队列： 会被存储在磁盘上，当消息代理重启时仍然存在
//         * exclusive: 默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列会被关闭
//         * autoDelete:是否自动删除，当没有生产者或者消费者使用队列，该队列会自动删除
//         * return new Queue("testDirectQueue", true, true, fasle,)
//         */
//        return new  Queue("TestDirectQueue", true);
//    }
//
//
//    /**
//     * 直连交换机
//     * @return
//     */
//    @Bean
//    DirectExchange testDirectExchange(){
//        return new DirectExchange("testDirectExchange", true, false);
//    }
//
//    /**
//     * 指定直连的队列与交换机，并且指定routyKey为：testDirectRouting
//     * @return
//     */
//    @Bean
//    Binding bindingDirect(){
//        return BindingBuilder.bind(testDirectQueue()).to(testDirectExchange()).with("testDirectRouting");
//    }
//
//
//    /**
//     * 重新创建一个直连的交换机
//     * @return
//     */
//    @Bean
//    DirectExchange lonelyDirectExchange(){
//        return new DirectExchange("lonelyDirectExchange");
//    }
//}
