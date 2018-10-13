package com.changhui.demo1;

import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyQueue {
    private Object obj;
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void writeObj(Object obj) {

        readWriteLock.writeLock().lock();
        try {
            this.obj = obj;
            System.out.println(Thread.currentThread().getName() + "\t" + obj);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    public void readObj() {

        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t" + obj);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }

    }

}

/**
 * @ClassName ReadWriteLockDemo
 * @Author changhui
 * @Description 一个线程写入100个线程读取;
 */

public class ReadWriteLockDemo {

    public static void main(String[] args) throws InterruptedException {

        MyQueue q = new MyQueue();

        new Thread(() -> {
            q.writeObj("nihao");
        }, "writeThread").start();

        Thread.sleep(100);

        for (int i = 1; i <= 100; i++) {
            new Thread(() -> {
                q.readObj();
            }, String.valueOf(i)).start();

        }
    }
}
