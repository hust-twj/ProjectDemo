package com.hust_twj.zademo.third_part.leak_canary;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hust_twj.zademo.R;

public class ThreadLeakActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_leak_canary);

        new MyThread().start();

    }

    public class MyThread extends Thread  {
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
                Log.d("twj", "执行了多线程");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
