package com.xian.ThreadPoolExecutorOp;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 当多个任务进来的时候，先开辟3个核心线程，然后将其余的线程放入队列中，只能放6个。
 * 当超过6个时候，会启动备用线程，一直维持队列中等待线程6个，会启动线程池能承受最多的线程直到15个，此时最多能支持的并发是15 + 6
 * @Author: Xian
 * @CreateDate: 2019/10/9  14:41
 * @Version: 0.0.1-SHAPSHOT
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 15, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(6));

        for(int i=0;i<7;i++){
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                    executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }
}


class MyTask implements Runnable {
    private int taskNum;

    public MyTask(int num) {
        this.taskNum = num;
    }

    @Override
    public void run() {
        System.out.println("正在执行task "+taskNum);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task "+taskNum+"执行完毕");
    }
}