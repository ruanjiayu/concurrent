package com.xian.thread.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: Xian
 * @CreateDate: 2019/10/8  16:46
 * @Version: 0.0.1-SHAPSHOT
 */
public class TryLockTest {

    private ArrayList<Integer> arrayList = new ArrayList<>();
    private Lock lock = new ReentrantLock();
    public static void main(String[] args)  {
        TryLockTest test = new TryLockTest();

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
        if(lock.tryLock()) {
            try {
                System.out.println(thread.getName()+"得到了锁");
//                Thread.sleep(2000);
                for(int i=0;i<50000;i++) {
                    arrayList.add(i);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }finally {
                System.out.println(thread.getName()+"释放了锁");
                lock.unlock();
            }
        } else {
            System.out.println(thread.getName()+"获取锁失败");
        }
    }
}
