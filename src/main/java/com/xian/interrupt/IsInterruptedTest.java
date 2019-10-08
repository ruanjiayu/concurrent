package com.xian.interrupt;

import java.io.IOException;

/**
 * @Description: 配合isInterrupted()能够中断正在运行的线程,但是我们一般使用子线程类中添加一个属性来标识结束while循环
 * @Author: Xian
 * @CreateDate: 2019/10/8  14:59
 * @Version: 0.0.1-SHAPSHOT
 */
public class IsInterruptedTest {

    public static void main(String[] args) throws IOException {
        IsInterruptedTest test = new IsInterruptedTest();
        MyThread thread = test.new MyThread();
        thread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {

        }
//        thread.interrupt();
        thread.setStop(true);
    }

    class MyThread extends Thread{
//        @Override
//        public void run() {
//            int i = 0;
//            while(!isInterrupted() && i<Integer.MAX_VALUE){
//                System.out.println(i+" while循环");
//                i++;
//            }
//        }

        private volatile boolean isStop = false;
        @Override
        public void run() {
            int i = 0;
            while(!isStop){
                System.out.println(i+" while循环");
                i++;
            }
        }

        public void setStop(boolean stop){
            this.isStop = stop;
        }
    }
}
