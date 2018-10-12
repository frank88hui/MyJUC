package com.changhui.demo1;

class ShareData {

    private int num = 0;

    public synchronized void increment() throws InterruptedException {
        //判断
        if (num != 0) {
            this.wait();
        }
        //干活
        ++num;
        System.out.println(Thread.currentThread().getName() + "\t" + num);

        //通知
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        if (num == 0) {
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
            for (int i = 0; i < 30; i++) {
                try {
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "increment").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareData.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "decrement").start();


    }


}
