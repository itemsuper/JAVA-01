package com.itemsuper.conc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.atomic.AtomicInteger;

public class CounterFlag {
    private static int result;

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池
        new Thread(() -> {
            result = sum();
            atomicInteger.addAndGet(1);
        }).start();

        //确保拿到结果并输出
        while (true){
            if (atomicInteger.get()==1){
                System.out.println("异步计算结果为：" + result);
                System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
                break;
            }

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
