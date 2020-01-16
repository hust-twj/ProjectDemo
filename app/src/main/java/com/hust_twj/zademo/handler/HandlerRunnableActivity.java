package com.hust_twj.zademo.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.Nullable;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.LogUtils;

/**
 * Handler + Runnable
 * Created by Wenjing.Tang
 * on 2019/12/02
 */
public class HandlerRunnableActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_runnable);

        final Handler handler = new Handler(getMainLooper());
        final HandlerRunnable runnable = new HandlerRunnable();

        new Thread(new Runnable() {
            @Override
            public void run() {
                LogUtils.e("twj124", "onCreate run " + Thread.currentThread().getName());

                Message message = Message.obtain(handler, runnable);
                handler.sendMessage(message);

                //以上等价
                //handler.post(runnable);
            }
        }).start();

        LogUtils.e("twj124", "onCreate  " + Thread.currentThread().getName());
    }

    class HandlerRunnable implements Runnable {

        @Override
        public void run() {
            LogUtils.e("twj124", "run  " + Thread.currentThread().getName());
        }
    }


}
