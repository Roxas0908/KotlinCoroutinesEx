package com.example.kotlincoroutineex;

import android.os.Handler;
import android.os.Looper;

import java.util.Locale;

/**
 * author: liumingsong
 * created on: 2025/7/4 10:08
 * description:
 */
public class MimesisWithContextDemo {

    public static void main(String[] args) {

        MimesisWithContextDemo demo = new MimesisWithContextDemo();

        demo.mimesisWithContext("", result -> {

        });

    }

    public void mimesisWithContext(String data, UpdateUI callback) {
        new Thread(() -> {
            String result = data.toLowerCase(Locale.ROOT);
            new Handler(Looper.getMainLooper()).post(() -> {
                callback.updateUI(result);
            });
        }).start();

    }

    interface UpdateUI {
        void updateUI(String result);
    }
}
