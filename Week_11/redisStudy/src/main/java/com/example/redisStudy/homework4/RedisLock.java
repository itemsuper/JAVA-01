package com.example.redisStudy.homework4;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisLock {

    @Autowired
    RedissonClient redisson;

    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
        // 1、获取一把锁，只要锁的名字一样，就是同一把锁
        RLock lock = redisson.getLock("item-lock");
        // 加锁
        // 阻塞式等待，默认加的锁都是【看门狗时间】30s时间
        //1)、锁的自动续期，如果业务超长，运行期间自动给锁续上新的30s，不用担心业务时间长，锁自动过期被删掉
        //2)、加锁的业务只要运行完成，就不会给当前锁续期，即使不手动解锁，锁默认在30s以后自动删除
        lock.lock();
        try {
            System.out.println("加锁成功......."+Thread.currentThread().getId());
            Thread.sleep(10000);
        } catch (InterruptedException e) {

        }finally {
            // 释放锁   不会出现死锁状态 如果没有执行解锁，锁有过期时间，过期了会将锁删除
            lock.unlock();
            System.out.println("解锁成功......"+Thread.currentThread().getId());
        }
        return "hello";
    }
}
