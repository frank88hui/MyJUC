package com.changhui.demo1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class Ticket {

    private static Integer num = 40;

    private Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();

            try { if (num > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第" + (num--) + "\t还剩:" + num); }
            } finally {
                lock.unlock();
            }

    }
}

public class SaleTicket {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) ticket.sale();
        }, "AA").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) ticket.sale();
        }, "BB").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) ticket.sale();
        }, "CC").start();
    }

}
