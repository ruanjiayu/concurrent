package com.xian.notifyOp;

/**
 * @Description: wait() 会释放锁，而notify会激活正在等待该对象锁的线程，但是不会立刻运行，而是要等到notify退出同步块，才开始执行.
 * notifyAll(),会激活所有等待该对象锁的线程，但是不能知道到底是哪一个先得到。
 * @Author: Xian
 * @CreateDate: 2019/10/10  10:28
 * @Version: 0.0.1-SHAPSHOT
 */
public class NotifyTest {

    public static Object object = new Object();
    public static void main(String[] args) {
        Thread1 thread1 = new Thread1("first");
        Thread2 thread2 = new Thread2("second");
        Thread3 thread3 = new Thread3("third");

        thread1.start();
        thread3.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.start();
    }

    static class Thread1 extends Thread{

        private String threadName;

        public Thread1(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            System.out.println(threadName +"进入");
            synchronized (object) {
                try {
                    object.wait();
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
                System.out.println( threadName + "获取到了锁");
            }
        }
    }

    static class Thread2 extends Thread{
        private String threadName;

        public Thread2(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            System.out.println(threadName + "进入");
            synchronized (object) {
                object.notify();
//                object.notifyAll();
                System.out.println(threadName + "调用了object.notify()");
            }
            System.out.println(threadName +"释放了锁");
        }
    }

    static class Thread3 extends Thread{

        private String threadName;

        public Thread3(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            System.out.println(threadName +"进入");
            synchronized (object) {
                try {
                    object.wait();
                } catch (InterruptedException e) {
                }
                System.out.println( threadName + "获取到了锁");
            }
        }
    }
}
