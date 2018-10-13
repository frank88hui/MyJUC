package com.changhui.demo1;

class ShareData {

    private int num = 0;

    public synchronized void increment() throws InterruptedException {
        //判断
        //多线程中重要的判断不要用if,要用while
        //if (num != 0) {
        while (num != 0) {
            this.wait();
        }
        //干活
        ++num;
        System.out.println(Thread.currentThread().getName() + "\t" + num);

        //通知
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        // if (num == 0) {
        while (num == 0) {
            this.wait();
        }
        --num;
        System.out.println(Thread.currentThread().getName() + "\t" + num);
        this.notifyAll();
    }

}


public class NotifyWaitDemo {

    public static void main(String[] args) {

        ShareData shareData = new ShareData();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
//                    Thread.sleep(200);
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
//                    Thread.sleep(300);
                    shareData.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
//                    Thread.sleep(400);
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "CC").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
//                    Thread.sleep(500);
                    shareData.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "DD").start();

    }


}
