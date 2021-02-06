package com.itemsuper.conc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchStudy {

    private static int result;

    public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();
        final CountDownLatch latch = new CountDownLatch(1);
        // 在这里创建一个线程或线程池，
        Executor threadPool = Executors.newFixedThreadPool(4);
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                result = sum(); //这是得到的返回值
                latch.countDown();
            }

        });
        //确保拿到结果并输出
        latch.await();
        System.out.println("异步计算结果为："+result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
