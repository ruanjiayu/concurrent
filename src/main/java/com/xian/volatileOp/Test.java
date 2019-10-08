package com.xian.volatileOp;

/**
 * @Description:
 * @Author: Xian
 * @CreateDate: 2019/10/8  18:56
 * @Version: 0.0.1-SHAPSHOT
 */
public class Test {
    public volatile int inc = 0;

    public synchronized  void increase() {
        inc++;
    }

    public static void main(String[] args) {
        final Test test = new Test();
        for(int i=0;i<10;i++){
            new Thread(){
                @Override
                public void run() {
                    for(int j=0;j<10000;j++) {
                        test.increase();
                    }
                };
            }.start();
        }
//保证前面的线程都执行完
        while(Thread.activeCount()>1)
        {
            Thread.yield();
        }
        System.out.println(test.inc);
    }
}
