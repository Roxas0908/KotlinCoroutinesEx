package com.example.kotlincoroutineex.chapter5;

import java.util.concurrent.locks.ReentrantLock;

/**
 * author: liumingsong
 * created on: 2026/2/14 11:33
 * description:
 */
public class SyncTest {
    int number = 0;
    ReentrantLock lock = new ReentrantLock();

    synchronized void numberPlus() {
        lock.lock();
        try {
            try {
                lock.lock();
            } finally {
                lock.unlock();
            }
            number++;
        } finally {
            lock.unlock();
        }
    }

    void numberMinus() {
        synchronized (this) {
            number--;
        }
    }

}

class Test {

    void test() {
        SyncTest syncTest = new SyncTest();

        new Thread() {
            @Override
            public void run() {
                super.run();
                syncTest.numberPlus();
            }
        };

        new Thread() {
            @Override
            public void run() {
                super.run();
                syncTest.numberMinus();
            }
        };

    }
}
