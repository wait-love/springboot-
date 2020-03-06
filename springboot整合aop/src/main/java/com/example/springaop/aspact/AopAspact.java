package com.example.springaop.aspact;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Jason
 * @Create: 2020/2/26  18:27
 * @Description aop切面类
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class AopAspact {

    /**
     * 切点为springaop包路径下面的所有
     */
    @Pointcut("execution(public * com.example.springaop..*.*(..))")
    public void webLog(){

    }

    @Before("webLog()")
    public  void doBefore(JoinPoint joinPoint) throws Throwable{
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //记录请求内容
        log.info("URL:" + request.getRequestURI().toString());
        //记录访问的ip地址
        log.info("ip：" + request.getRemoteAddr());
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturn(Object ret) throws Throwable{
        //处理完请求，返回内容
        log.info("RESPONSE:" + ret);
    }
}
