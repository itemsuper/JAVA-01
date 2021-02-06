package com.itemsuper.conc;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    private static int result;

    public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池
        SemaphoreTest semaphoreTest = new SemaphoreTest();
        //只允许一个线程获取资源，如果一方获取了执行信号就可以执行任务，没有获取执行信号就阻塞直到对方释放执行信号
        Semaphore semaphore = new Semaphore(1);
        new Thread(semaphoreTest.new AThread(semaphore)).start();
        //确保拿到结果并输出
        new Thread(semaphoreTest.new BThread(semaphore,start)).start();


    }

    // 创建线程A用于执行sum操作
    class AThread implements Runnable {
        Semaphore semaphore;

        public AThread(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();//获取执行许可，阻塞线程
                result = sum(); //这是得到的返回值
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                semaphore.release();//如果不加这句话，信号永远不会释放，线程卡死
            }

        }

    }

    // 创建线程B用于执行sum操作
    class BThread implements Runnable {
        Semaphore semaphore;
        Long start;
        public BThread(Semaphore semaphore,long start) {
            this.semaphore = semaphore;
            this.start = start;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();//获取执行许可，阻塞线程
                System.out.println("异步计算结果为：" + result);
                System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                semaphore.release();//如果不加这句话，信号永远不会释放，线程卡死
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
