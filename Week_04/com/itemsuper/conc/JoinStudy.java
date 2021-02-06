package com.itemsuper.conc;

public class JoinStudy {
    private static int result;

    public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池
        Thread childThread = new Thread(new Runnable() {
            @Override
            public void run() {
                result = sum(); //这是得到的返回值
            }
        });
        childThread.start();

        //确保拿到结果并输出
        childThread.join();
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
