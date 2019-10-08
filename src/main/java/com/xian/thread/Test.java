package com.xian.thread;

/**
 * @Description:
 * @Author: Xian
 * @CreateDate: 2019/10/8  13:53
 * @Version: 0.0.1-SHAPSHOT
 */
public class Test {
    public static void main(String[] args)  {
        System.out.println("主线程ID:"+Thread.currentThread().getId());
        MyThread thread1 = new MyThread("thread1");
        thread1.start();
        MyThread thread2 = new MyThread("thread2");
        thread2.run();
    }
}
