package com.xian.runnable;

/**
 * @Description:
 * @Author: Xian
 * @CreateDate: 2019/10/8  13:59
 * @Version: 0.0.1-SHAPSHOT
 */
public class MyRunnable implements Runnable {

    public MyRunnable() {

    }

    @Override
    public void run() {
        System.out.println("子线程ID："+Thread.currentThread().getId());
    }
}
