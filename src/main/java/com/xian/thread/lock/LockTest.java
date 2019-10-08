package com.xian.thread.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: Xian
 * @CreateDate: 2019/10/8  16:30
 * @Version: 0.0.1-SHAPSHOT
 */
public class LockTest {
    private ArrayList<Integer> arrayList = new ArrayList<>();
    /**
     * 上升为类的属性
     */
    private Lock lock = new ReentrantLock();
    public static void main(String[] args)  {
        final LockTest test = new LockTest();

        new Thread(){
            @Override
            public void run() {
                test.insert(Thread.currentThread());
            };
        }.start();

        new Thread(){
            @Override
            public void run() {
                test.insert(Thread.currentThread());
            };
        }.start();
    }

    public void insert(Thread thread) {
//        Lock lock = new ReentrantLock(); 注意这个地方，创建了不同的lock，是局部变量，所以不能上锁
        lock.lock();
        try {
            System.out.println(thread.getName()+"得到了锁");
            for(int i=0;i<500;i++) {
                arrayList.add(i);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }finally {
            System.out.println(thread.getName()+"释放了锁");
            lock.unlock();
        }
    }

}
