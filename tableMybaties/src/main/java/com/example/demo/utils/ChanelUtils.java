package com.example.demo.utils;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.stereotype.Component;

import java.nio.channels.Channel;

/**
 * @Author: Jason
 * @Create: 2020/12/12  21:25
 * @Description 获取到信道的工具类
 */

@Component
public class ChanelUtils {
    //host
    private static final String HOST = "127.0.0.1";

    //port
    private static final Integer PORT = 5672;

    //用户名
    private static final String USERNAME = "guest";

    //密码
    private static final String PASSWORD = "guest";


}
