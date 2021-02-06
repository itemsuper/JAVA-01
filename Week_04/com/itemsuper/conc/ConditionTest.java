package com.itemsuper.conc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    private static int result;
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池
        Thread childThread = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    result = sum(); //这是得到的返回值
                    condition.signal();
                }finally {
                    lock.unlock();
                }
            }
        });
        childThread.start();

        //确保拿到结果并输出
        lock.lock();
        try {
            condition.await();
            System.out.println("异步计算结果为：" + result);
            System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        }finally {
            lock.unlock();
        }

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
