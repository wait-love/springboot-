package com.example.demo.service;

/**
 * @Author: Jason
 * @Create: 2020/11/22  15:25
 * @Description 分布式锁的管理类
 */
public interface DistributedLocker {

    /**
     * 获取锁时需要填写的参数
     * @param resourceName
     * @param worker
     * @param <T>
     * @return
     * 
     * @throws Exception
     */
    <T> T lock(String resourceName, AquiredLockWorker<T> worker) throws Exception;


    /**
     * 获取锁时候的需要填写参数，同时设置了锁的有效期
     * @param <T>
     * @param resourceName
     * @param worker
     * @param time
     * @throws Exception
     */
    <T> T lock(String resourceName, AquiredLockWorker<T> worker, long time) throws Exception;
}
