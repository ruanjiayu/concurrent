package com.xian.volatileOp;

/**
 * @Description: 还是会存在初始化多次SingleTon的存在
 * @Author: Xian
 * @CreateDate: 2019/10/8  19:47
 * @Version: 0.0.1-SHAPSHOT
 */
public class DoubleCheckTest {
}


class Singleton {
    private volatile static Singleton instance = null;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}