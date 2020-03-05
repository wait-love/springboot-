package com.jason.feign;

import com.jason.kafka.entity.Student;
import feign.Headers;
import feign.RequestLine;

import java.util.List;

/**
 * @Author: Jason
 * @Create: 2020/3/3  15:28
 * @Description feign测试类
 */
public interface FeignTest {

    @Deprecated
    @RequestLine("GET /message2")
    @Headers("Content-Type: application/json")
    List<Student> send2();
}
