package com.example.demo.dds;

import java.lang.annotation.*;

/**
 * @Author: Jason
 * @Create: 2020/11/19  19:49
 * @Description 定义一个数据源的注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    /**
     * 数据源的key值
     * @return
     */
    String value();
}
