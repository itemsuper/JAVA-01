package com.itemsuper.conc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableStudy {
    private static int result;

    public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池
        FutureTask<Integer> futureTask = new FutureTask(new Mythread());
        new Thread(futureTask,"callableThread").start();
        Integer result = null;
        try {
            result = futureTask.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //确保拿到结果并输出
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");


    }

    static class Mythread implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            result = sum(); //这是得到的返回值
            return result;
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
