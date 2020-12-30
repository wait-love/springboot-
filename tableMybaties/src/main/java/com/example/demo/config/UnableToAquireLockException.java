package com.example.demo.config;

/**
 * @Author: Jason
 * @Create: 2020/11/22  15:46
 * @Description 未拿取到锁的异常
 */
public class UnableToAquireLockException extends RuntimeException {


    /**
     * 定义一个无参构造
     */
    public UnableToAquireLockException(){};

    /**
     * 打印出错误的信息
     * @param message
     */
    public UnableToAquireLockException(String message){
        super(message);
    }

    /**
     * 打印错误信息与异常类型
     * @param message
     * @param cause
     */
    public UnableToAquireLockException(String message, Throwable cause){
        super(message, cause);
    }
}
