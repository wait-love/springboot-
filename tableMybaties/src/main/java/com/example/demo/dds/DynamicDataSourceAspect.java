package com.example.demo.dds;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: Jason
 * @Create: 2020/11/19  20:00
 * @Description  注意这个类中的前置通知与后置通知
 */

@Aspect
@Order(-1)
@Component
public class DynamicDataSourceAspect {

    /**
     * 这个注解只是启动确保ThreadLocal中有唯一的字符串
     * @param point
     * @param dataSource
     */
    @Before("@annotation(dataSource))")
    public void switchDataSource(JoinPoint point, DataSource dataSource){
        if (!DynamicDataSourcecontentHolder.containDataSourceKey(dataSource.value())){
            System.out.println("DataSource [{}] doesn't exist, use default DataSource [{}] " + dataSource.value());
        }else{
            DynamicDataSourcecontentHolder.setDataSourceKey(dataSource.value());
        }
    }

    /**
     *对目标方法调用之后通知
     * @param point
     * @param dataSource
     */
    @After("@annotation(dataSource))")
    public void resetDataSource(JoinPoint point, DataSource dataSource){
        DynamicDataSourcecontentHolder.clearDataSourceKey();
    }
}
