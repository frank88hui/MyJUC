package com.changhui.demo1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData2 {

    private int num = 0;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();


    public void increment() {


        lock.lock();
        try {
            //条件
            while (num != 0) {
                condition.await();
            }
            //干活
            ++num;
            System.out.println(Thread.currentThread().getName() + "\t" + num);

            //通知
            condition.signalAll();

        } catch (Exception e) {

        } finally {
            lock.unlock();
        }

    }

    public void decrement() {


        lock.lock();
        try {
            //条件
            while (num == 0) {
                condition.await();
            }
            //干活
            --num;
            System.out.println(Thread.currentThread().getName() + "\t" + num);

            //通知
            condition.signalAll();

        } catch (Exception e) {

        } finally {
            lock.unlock();
        }

    }


    public static void main(String[] args) {
        ShareData2 shareData = new ShareData2();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareData.increment();
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareData.decrement();
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {

                shareData.increment();
            }
        }, "CC").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareData.decrement();
            }
        }, "DD").start();
    }


}