package com.xian.helperOp;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Description: 1. 从结果可以看出，当四个线程都到达barrier状态后，会从四个线程中选择一个线程去执行Runnable。
 * 2. 通过它可以实现让一组线程等待至某个状态之后再全部同时执行。
 * 叫做回环是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用。我们暂且把这个状态就叫做barrier，当调用await()方法之后，线程就处于barrier了。
 * @Author: Xian
 * @CreateDate: 2019/10/10  9:01
 * @Version: 0.0.1-SHAPSHOT
 */
public class CyclicBarrierTest {
//    public static void main(String[] args) {
//        int N = 4;
//        CyclicBarrier barrier = new CyclicBarrier(N, new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("当前线程" + Thread.currentThread().getName());
//            }
//        });
//
//        for (int i = 0; i < N; i++) {
//            new Writer(barrier).start();
//        }
//    }
//
//    static class Writer extends Thread {
//        private CyclicBarrier cyclicBarrier;
//
//        public Writer(CyclicBarrier cyclicBarrier) {
//            this.cyclicBarrier = cyclicBarrier;
//        }
//
//        @Override
//        public void run() {
//            System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据...");
//            try {
//                Thread.sleep(5000);      //以睡眠来模拟写入数据操作
//                System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕");
//                cyclicBarrier.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (BrokenBarrierException e) {
//                e.printStackTrace();
//            }
//            System.out.println("所有线程写入完毕，继续处理其他任务...");
//        }
//    }

    public static void main(String[] args) {
        int N = 4;
        CyclicBarrier barrier = new CyclicBarrier(N);

        for (int i = 0; i < N; i++) {
            new Writer(barrier).start();
        }

        try {
            Thread.sleep(25000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("CyclicBarrier重用");

        for (int i = 0; i < N; i++) {
            new Writer(barrier).start();
        }
    }

    static class Writer extends Thread {
        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据...");
            try {
                Thread.sleep(5000);      //以睡眠来模拟写入数据操作
                System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕");

                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "所有线程写入完毕，继续处理其他任务...");
        }
    }
}
