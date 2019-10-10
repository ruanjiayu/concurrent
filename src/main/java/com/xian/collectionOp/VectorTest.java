package com.xian.collectionOp;

import java.util.Vector;

/**
 * @Description: 同步容器不是真的安全，这段代码报错了：数组下标越界, 解决方法:添加代码同步块，并且锁内的类必须是一样的才生效
 * @Author: Xian
 * @CreateDate: 2019/10/9  10:05
 * @Version: 0.0.1-SHAPSHOT
 */
public class VectorTest {

    static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }
            Thread thread1 = new Thread() {
                @Override
                public void run() {
                    synchronized (VectorTest.class) {   //进行额外的同步
                        for (int i = 0; i < vector.size(); i++) {
                            vector.remove(i);
                        }
                    }
                }
            };
            Thread thread2 = new Thread() {
                @Override
                public void run() {
                    synchronized (VectorTest.class) {   //进行额外的同步
                        for (int i = 0; i < vector.size(); i++) {
                            vector.get(i);
                        }
                    }
                }
            };
            thread1.start();
            thread2.start();
        }
    }
}
