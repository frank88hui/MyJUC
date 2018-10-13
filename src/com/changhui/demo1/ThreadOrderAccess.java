package com.changhui.demo1;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource {
    private int num = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5(int totalLoop) {
        lock.lock();
        try {
            //1判断
            while (num != 1) {
                c1.await();
            }
            //2干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + (i + 1)+ "\t"  + "第" + totalLoop + "轮");
            }
            //3通知
            num = 2;
            //点通知
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10(int totalLoop) {
        lock.lock();
        try {
            //1判断
            while (num != 2) {
                c2.await();
            }
            //2干活
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + (i + 1)+ "\t"  + "第" + totalLoop + "轮");
            }
            //3通知
            num = 3;
            //点通知
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15(int totalLoop) {
        lock.lock();
        try {
            //1判断
            while (num != 3) {
                c3.await();
            }
            //2干活
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + (i + 1)+ "\t"  + "第" + totalLoop + "轮");
            }
            //3通知
            num = 1;
            //点通知
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

/**
 * @ClassName ThreadOrderAccess
 * @Author changhui
 * @Date 2018/10/13
 * @Version 1.0
 * @Description
 */
public class ThreadOrderAccess {


    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print5(i);
            }

        }, "AA").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print10(i);
            }

        }, "BB").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print15(i);
            }

        }, "CC").start();
    }

}
