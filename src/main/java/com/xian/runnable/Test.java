package com.xian.runnable;

/**
 * @Description:
 * @Author: Xian
 * @CreateDate: 2019/10/8  13:59
 * @Version: 0.0.1-SHAPSHOT
 */
public class Test {
    public static void main(String[] args)  {
        System.out.println("主线程ID："+Thread.currentThread().getId());
        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
