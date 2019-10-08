package com.xian.thread.synchronize;

import java.util.ArrayList;

/**
 * @Description: 同一类型的不同对象，访问synchronized方法，不会出现线程安全问题。因为是不同对象。
 * 访问同一对象内加锁的不同方法，需要按顺序执行.除非一个访问类中的同步锁，一个访问对象的锁，那么不存在互斥现象。
 * 对于synchronized方法或者synchronized代码块，当出现异常时，JVM会自动释放当前线程占用的锁，因此不会由于异常导致出现死锁现象
 * @Author: Xian
 * @CreateDate: 2019/10/8  15:19
 * @Version: 0.0.1-SHAPSHOT
 */
public class Test {
        public static void main(String[] args)  {
        final InsertData insertData = new InsertData();

        new Thread() {
            @Override
            public void run() {
                insertData.insert();
            }
        }.start();


        new Thread() {
            @Override
            public void run() {
                InsertData.insert1();
            }
        }.start();
    }
}

class InsertData {
    private ArrayList<Integer> arrayList = new ArrayList<>();

    public synchronized void insert(Thread thread){
        for(int i=0;i<500;i++){
            System.out.println(thread.getName()+"在插入数据"+i);
            arrayList.add(i);
        }
    }

    public synchronized void insert(){
        System.out.println("执行insert");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行insert完毕");
    }

    public static synchronized  void insert1() {
        System.out.println("执行insert1");
        System.out.println("执行insert1完毕");
    }


//    public static void main(String[] args) {
//
//        new MyThread().start();
//        new MyThread().start();
//    }
//}
//
//class MyThread extends Thread {
//    // fixme 为什么一定要带上static，不能仅仅是final
//    private static InsertData insertData = new InsertData();
//
//    @Override
//    public void run() {
//        insertData.insert(Thread.currentThread());
//    }
//}
//
//
//class InsertData {
//    private ArrayList<Integer> arrayList = new ArrayList<>();
//
//    public synchronized void insert(Thread thread) {
//        for (int i = 0; i < 500; i++) {
//            System.out.println(thread.getName() + "在插入数据" + i);
//            arrayList.add(i);
//        }
//    }
}
