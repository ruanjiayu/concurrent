package com.xian.thread.synchronize.syncObject;

import java.util.ArrayList;

/**
 * @Description: synchronized代码块使用起来比synchronized方法要灵活得多。
 * 因为也许一个方法中只有一部分代码只需要同步，如果此时对整个方法用synchronized进行同步，会影响程序执行效率。
 * 而使用synchronized代码块就可以避免这个问题，synchronized代码块可以实现只对需要同步的地方进行同步。
 * @Author: Xian
 * @CreateDate: 2019/10/8  15:19
 * @Version: 0.0.1-SHAPSHOT
 */
public class Test {
    public static void main(String[] args) {

        new MyThread().start();
        new MyThread().start();
    }
}

class MyThread extends Thread {
    static InsertData insertData = new InsertData();

    @Override
    public void run() {
        insertData.insert(Thread.currentThread());
    }
}

/**
 * 第一种方法：synObject可以是this，代表获取当前对象的锁
 */
//class InsertData {
//    private ArrayList<Integer> arrayList = new ArrayList<>();
//
//    public void insert(Thread thread){
//        synchronized (this) {
//            for(int i=0;i<100;i++){
//                System.out.println(thread.getName()+"在插入数据"+i);
//                arrayList.add(i);
//            }
//        }
//    }
//}

/**
 * 第二种方法:类中的一个属性，代表获取该属性的锁
 */
class InsertData {
    private ArrayList<Integer> arrayList = new ArrayList<>();
    private Object object = new Object();

    public void insert(Thread thread) {
        synchronized (object) {
            for (int i = 0; i < 100; i++) {
                System.out.println(thread.getName() + "在插入数据" + i);
                arrayList.add(i);
            }
        }
    }
}