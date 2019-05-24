package com.hust_twj.zademo.thread;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.LogUtils;

import java.util.concurrent.CountDownLatch;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by hust_twj
 * on 2019/2/26
 */
public class ThreadActivity extends Activity {

    private CountDownLatch countDownLatch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_thread);

        countDownLatch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    boolean result = countDownLatch.await(5, SECONDS);
                    LogUtils.e("twj124", "countDownLatch  " + result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LogUtils.e("twj124", "countDownLatch  " + Thread.currentThread().getName() + "  " + countDownLatch.getCount());
            }
        }).start();

        LogUtils.e("twj124", "countDownLatch  " + Thread.currentThread().getName());

        countDownLatch.countDown();

    }

}
