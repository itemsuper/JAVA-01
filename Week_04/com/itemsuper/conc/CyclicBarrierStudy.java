package com.itemsuper.conc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierStudy {
    private static int result;

    public static void main(String[] args) throws InterruptedException {
        final CyclicBarrier barrier = new CyclicBarrier(2);

        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池
        new Thread(() -> {
            result = sum();
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            }).start();

        //确保拿到结果并输出
        try {
            barrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");


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
