package com.hust_twj.zademo.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.hust_twj.zademo.R;


/**
 * IdlerHandler
 * Created by Wenjing.Tang
 * on 2019/7/27
 */
public class IdlerHandlerActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idle_handler);

        Log.e("twj125", "onCreate：" + System.currentTimeMillis());
        Looper.myQueue().addIdleHandler(new MyIdleHandler());
    }

    public void click(View view) {
        Log.e("twj125", "click start：" + System.currentTimeMillis());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("twj125", "click   end：" + System.currentTimeMillis());
    }

    class MyIdleHandler implements MessageQueue.IdleHandler {

        @Override
        public boolean queueIdle() {
            Log.e("twj125", "queueIdle start：" + System.currentTimeMillis());
            new MyThread().start();
            Log.e("twj125", "queueIdle   end：" + System.currentTimeMillis());
            return true;
        }
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            super.run();
        }
    }

}
