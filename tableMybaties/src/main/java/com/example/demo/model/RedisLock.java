package com.example.demo.model;

import com.example.demo.service.AquiredLockWorker;
import com.example.demo.service.DistributedLocker;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Jason
 * @Create: 2020/11/22  15:30
 * @Description redis通过RedissonConnector实现
 */

@Component
public class RedisLock implements DistributedLocker {

    private final static String name = "redisLock";
    
    @Autowired
    private RedissonConnector redissonConnector;
    
    
    @Override
    public <T> T lock(String resourceName, AquiredLockWorker<T> worker) throws Exception {
        return lock(resourceName, worker, 100);
    }

    @Override
    public <T> T lock(String resourceName, AquiredLockWorker<T> worker, long time) throws Exception {
        RedissonClient redissonClient = redissonConnector.getRedissonClient();
        RLock lock = redissonClient.getLock(name + resourceName);
        //等待100秒释放锁
        boolean flag = lock.tryLock(100, time, TimeUnit.SECONDS);
        if(flag){
            //代码必须这样设计
            try{
                //拿取到锁之后执行的业务的方法
                return worker.invokeAfterLockAquire();
            }finally {
                lock.unlock();
            }
        }

        //没有拿取到锁时，会报没有拿取锁异常
        throw new UnsupportedOperationException();
    }
}
