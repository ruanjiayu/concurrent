package com.xian.thread;

/**
 * @Description: 继承thread，注意需要实现run方法
 * @Author: Xian
 * @CreateDate: 2019/10/8  13:51
 * @Version: 0.0.1-SHAPSHOT
 */
public class MyThread extends Thread{
    private String name;

    public MyThread(String name){
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("name:"+name+" 子线程ID:"+Thread.currentThread().getId());
    }
}
