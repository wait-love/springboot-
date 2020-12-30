package com.example.demo.service;

/**
 * @Author: Jason
 * @Create: 2020/11/22  15:23
 * @Description 获取到分布式锁之后的操作类
 */
public interface AquiredLockWorker<T> {

    /**
     * 获取锁之后处理具体业务逻辑的方法
     * @return
     * @throws Exception
     */
    T invokeAfterLockAquire() throws Exception;
}
