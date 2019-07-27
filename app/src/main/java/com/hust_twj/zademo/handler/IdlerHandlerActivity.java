package com.hust_twj.zademo.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
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

    private HandlerThread handlerThread ;
    private Handler mHandler;

    private MyIdleHandler mIdleHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idle_handler);

        handlerThread = new HandlerThread("handlerThread");
        handlerThread.start();
        Log.e("twj125", "onCreate：" + System.currentTimeMillis() +  "  " + Thread.currentThread().getName());
        mIdleHandler = new MyIdleHandler();
        Looper.myQueue().addIdleHandler(mIdleHandler);

        mHandler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //子线程中执行 模拟耗时操作
                Log.e("twj125", "handleMessage start：" + System.currentTimeMillis() +  "  " + Thread.currentThread().getName());
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e("twj125", "handleMessage   end：" + System.currentTimeMillis() +  "  " + Thread.currentThread().getName());
            }
        };
    }

    public void click(View view) {
        //主线程中执行
        Log.e("twj125", "click start：" + System.currentTimeMillis());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("twj125", "click   end：" + System.currentTimeMillis());
    }

    class MyIdleHandler implements MessageQueue.IdleHandler {

        @Override
        public boolean queueIdle() {
            Log.e("twj125", "queueIdle：" + System.currentTimeMillis() +  "  " + Thread.currentThread().getName());
            if (mHandler != null) {
                Message message = Message.obtain();
                mHandler.sendMessage(message);
            }
            return true;
        }
    }

    @Override
    protected void onDestroy() {
        handlerThread.quitSafely();
        mHandler.removeCallbacksAndMessages(null);
        mHandler = null;
        //移除IdleHandle， 防止queueIdle()一直执行
        Looper.myQueue().removeIdleHandler(mIdleHandler);
        super.onDestroy();
    }

}
