package com.xian.volatileOp;

/**
 * @Description: 1. 保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的
 * 2. 禁止进行指令重排序。
 * @Author: Xian
 * @CreateDate: 2019/10/8  18:56
 * @Version: 0.0.1-SHAPSHOT
 */
public class Test {
    public volatile int inc = 0;

    public synchronized void increase() {
        inc++;
    }

    public static void main(String[] args) {
        final Test test = new Test();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        test.increase();
                    }
                }

                ;
            }.start();
        }
//保证前面的线程都执行完
        Thread.currentThread().getThreadGroup().list();

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(test.inc);
    }
}
