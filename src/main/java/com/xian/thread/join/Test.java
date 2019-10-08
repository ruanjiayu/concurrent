package com.xian.thread.join;

import java.io.IOException;

/**
 * @Description: 如果调用的是无参join方法，则等待thread执行完毕，如果调用的是指定了时间参数的join方法，则等待一定的时间。内部方法是wait
 * @Author: Xian
 * @CreateDate: 2019/10/8  14:37
 * @Version: 0.0.1-SHAPSHOT
 */
public class Test {

    public static void main(String[] args) throws IOException {
        System.out.println("进入线程"+Thread.currentThread().getName());
        Test test = new Test();
        MyThread thread1 = test.new MyThread();
        thread1.start();
        try {
            System.out.println("线程"+Thread.currentThread().getName()+"等待");
            thread1.join(1000);
            System.out.println("线程"+Thread.currentThread().getName()+"继续执行");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("进入线程"+Thread.currentThread().getName());
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                // TODO: handle exception
            }
            System.out.println("线程"+Thread.currentThread().getName()+"执行完毕");
        }
    }
}
