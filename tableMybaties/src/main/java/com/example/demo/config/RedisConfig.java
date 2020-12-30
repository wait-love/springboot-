package com.example.demo.config;

import com.example.demo.redis.MessageReceive;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Author: Jason
 * @Create: 2020/11/22  9:37
 * @Description redis配置类‘
 */

@Configuration
public class RedisConfig {


    /**
     * 自定义一个redis模板并且实现序列化
     * @param factory
     * @return
     */
    @Bean
    @SuppressWarnings("all")  //作用告诉编辑器不要在编译完成后出现警告信息
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory){
        //引入原来的redisTemplate来实现注入
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        //将工厂注入进stringTemplate中
        template.setConnectionFactory(factory);

        //采用了jackSon序列化对象
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        //对String进行序列化
        StringRedisSerializer stringRedisTemplate = new StringRedisSerializer();
        template.setKeySerializer(stringRedisTemplate);
        template.setHashKeySerializer(stringRedisTemplate);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashKeySerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }


    /**
     * redis消息监听器
     * 可以添加多个监听不同话题的redis监听器
     * 通过反射技术调用消息订阅处理器的相关方法进行一些业务处理
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //订阅通道的地方
        container.addMessageListener(listenerAdapter, new PatternTopic("chat"));
        //这个container 可以添加多个 messageLister
        return container;
    }


    /**
     * 消息监听器适配器，绑定消息处理器，利用反射技术调用消息处理器的业务方法
     * @param receiveMessage
     * @return
     */
    @Bean
    MessageListenerAdapter  listenerAdapter(MessageReceive receiveMessage){
        //作用是给messageListernerAdater传入一个消息接收的处理器，利用反射调用方法
        return new MessageListenerAdapter(receiveMessage, "receive");
    }
}
