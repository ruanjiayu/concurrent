package com.xian.thread.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description: 有一个线程已经占用了读锁，则此时其他线程如果要申请写锁，则申请写锁的线程会一直等待释放读锁
 * 有一个线程已经占用了写锁，则此时其他线程如果申请写锁或者读锁，则申请的线程会一直等待释放写锁
 * Lock可以提高多个线程进行读操作的效率
 * @Author: Xian
 * @CreateDate: 2019/10/8  17:05
 * @Version: 0.0.1-SHAPSHOT
 */
public class ReentrantReadWriteLockTest {

    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args)  {
        final ReentrantReadWriteLockTest test = new ReentrantReadWriteLockTest();

        new Thread(){
            @Override
            public void run() {
                test.get(Thread.currentThread());
            };
        }.start();

        new Thread(){
            @Override
            public void run() {
                test.get(Thread.currentThread());
            };
        }.start();

    }

    public void get(Thread thread) {
        rwl.readLock().lock();
        try {
            long start = System.currentTimeMillis();

            for(int i = 0; i < 5000; ++i) {
                System.out.println(thread.getName()+"正在进行读操作");
            }
            System.out.println(thread.getName()+"读操作完毕");
        } finally {
            rwl.readLock().unlock();
        }
    }
}
