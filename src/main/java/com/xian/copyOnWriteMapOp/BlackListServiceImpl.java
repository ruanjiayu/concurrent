package com.xian.copyOnWriteMapOp;

import java.util.Map;

/**
 * @Description: 当我们往一个容器添加元素的时候，不直接往当前容器添加，
 * 而是先将当前容器进行Copy，复制出一个新的容器，然后新的容器里添加元素，添加完元素之后，再将原容器的引用指向新的容器
 * 所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器
 * @Author: Xian
 * @CreateDate: 2019/10/9  13:36
 * @Version: 0.0.1-SHAPSHOT
 */
public class BlackListServiceImpl {

    private static CopyOnWriteMap<String, Boolean> blackListMap = new CopyOnWriteMap<>();

    public static boolean isBlackList(String id) {
        return blackListMap.get(id) == null ? false : true;
    }

    public static void addBlackList(String id) {
        blackListMap.put(id, Boolean.TRUE);
    }

    /**
     * 批量添加黑名单
     *
     * @param ids
     */
    public static void addBlackList(Map<String,Boolean> ids) {
        blackListMap.putAll(ids);
    }
}
