package com.example.kotlincoroutineex.chapter5;

/**
 * author: liumingsong
 * created on: 2026/2/16 17:21
 * description:
 */
public class ThreadLocalTest {

    ThreadLocal<String> localString = new ThreadLocal<>();

    void threadLocal() {
        String string = localString.get();
        localString.set("roxas");

        String distinctString = "sora";

        new Thread() {
            @Override
            public void run() {
                super.run();
                String innerString = localString.get();
            }
        }.start();


    }
}
