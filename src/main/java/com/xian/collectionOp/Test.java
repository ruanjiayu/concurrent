package com.xian.collectionOp;

import java.util.ArrayList;
import java.util.Vector;

/**
 * @Description: Vector和ArrayList在插入数据时性能上的差异
 * Vector的性能比ArrayList数据多的时候要好，单线程的话，上锁解锁的时间可以忽略，但是，arraylist和vector都是可变数组，当容量不够的时候，arraylist是增加自身的一半，
 * 而vector是增加一倍，从这里看vector要省很多时间在增加容量上。也许这就是优势所在。
 * @Author: Xian
 * @CreateDate: 2019/10/9  9:59
 * @Version: 0.0.1-SHAPSHOT
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Integer> list = new ArrayList<>();
        Vector<Integer> vector = new Vector<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("ArrayList进行100000次插入操作耗时：" + (end - start) + "ms");
        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            vector.add(i);
        }
        end = System.currentTimeMillis();
        System.out.println("Vector进行100000次插入操作耗时：" + (end - start) + "ms");
    }
}
