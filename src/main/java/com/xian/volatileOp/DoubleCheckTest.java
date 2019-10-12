package com.xian.volatileOp;

/**
 * @Description: 双重检测同步延迟加载
 * @Author: Xian
 * @CreateDate: 2019/10/8  19:47
 * @Version: 0.0.1-SHAPSHOT
 */
public class DoubleCheckTest {
    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                try {
                    Singleton.getInstance();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                try {
                    Singleton.getInstance();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                try {
                    Singleton.getInstance();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}


class Singleton {
    private volatile static Singleton instance = null;

    private Singleton() {

    }

    public static Singleton getInstance() throws InterruptedException {
        if (instance == null) {
            Thread.sleep(5000);
            synchronized (Singleton.class) {
                System.out.println("突破第一关");
                if (instance == null) {
                    System.out.println("一夫当关");
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}