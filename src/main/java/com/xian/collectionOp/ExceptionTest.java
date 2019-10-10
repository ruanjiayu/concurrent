package com.xian.collectionOp;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @Description:
 * @Author: Xian
 * @CreateDate: 2019/10/9  10:30
 * @Version: 0.0.1-SHAPSHOT
 */
public class ExceptionTest {

    public static void main(String[] args)  {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(3);
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            Integer integer = iterator.next();
            if(integer==2) {
                list.remove(integer);
//                iterator.remove();   //注意这个地方,在单线程下可以使用
            }
        }
    }
}
