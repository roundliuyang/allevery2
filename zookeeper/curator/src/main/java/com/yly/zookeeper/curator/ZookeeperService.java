package com.yly.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
public class ZookeeperService {


    @Autowired
    private CuratorFramework curatorFramework;

    private static final Integer threadSize = 50;


    private static Integer number = 0;


    public Integer getNumber() throws InterruptedException {


        CountDownLatch countDownLatch = new CountDownLatch(threadSize);

        for (int i = 1; i <= threadSize; i++) {
            new Thread(() -> {
                InterProcessMutex interProcessMutex = new InterProcessMutex(curatorFramework, "/lock_");
                try {
                    // 多线程并发，同一时间只有一个线程获取到锁
                    interProcessMutex.acquire();
                    for (int j = 1; j <= 100; j++) {
                        number++;
                    }
                    System.out.println(Thread.currentThread().getName() + "->" + number);
                    countDownLatch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        interProcessMutex.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }, "t" + i).start();
        }

        countDownLatch.await();

        return number;
    }


}