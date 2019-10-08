package com.xian.interrupt;

import java.io.IOException;

/**
 * @Description: interrupt方法可以中断处于阻塞状态的线程，不能中断处于非阻塞状态的线程
 * @Author: Xian
 * @CreateDate: 2019/10/8  14:45
 * @Version: 0.0.1-SHAPSHOT
 */
public class Test {

    public static void main(String[] args) throws IOException {
        Test test = new Test();
        MyThread thread = test.new MyThread();
        thread.start();
        try {
            // 主线程进行睡眠
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
        // 中断子线程的睡眠
        thread.interrupt();
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            try {
                System.out.println("进入睡眠状态");
                // 子线程进行睡眠
                sleep(10000);
                System.out.println("睡眠完毕");
            } catch (InterruptedException e) {
                System.out.println("得到中断异常");
            }
            System.out.println("run方法执行完毕");
        }
    }
}
