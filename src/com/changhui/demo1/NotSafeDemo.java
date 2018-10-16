package com.changhui.demo1;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class NotSafeDemo {

    public static void main(String[] args) {
        listNotSafe();


    }

    private static void mapNotSafe() {
        // HashMap<String, String> map = new HashMap<>();

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 300; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 4));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

    private static void setNotSafe() {
        //HashSet<String> set = new HashSet<>();

        Set<String> set = new CopyOnWriteArraySet<String>();

        for (int i = 0; i < 300; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 4));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    private static void listNotSafe() {
        //ConcurrentModificationException并发修改异常
        //List<String> list = new Vector<>();
        List<String> list = new CopyOnWriteArrayList<>();//写性能较差,读性能较好
        //List<String> list = Collections.synchronizedList(list1);//写性能较好,读性能较差

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 4));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

}
