package com.hust_twj.zademo.handler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.LogUtils;

import java.lang.ref.WeakReference;

public class HandlerActivity extends Activity implements View.OnClickListener {

    private TextView mTvSend, mTvReceive, mTvUpdate;


    private MyHandler myHandler;
    private static final int MSG_UPDATE = 1;

    private HandlerThread handlerThread;
    private Handler mThreadHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_handler);

        mTvSend = findViewById(R.id.tv_send);
        mTvReceive = findViewById(R.id.tv_receive);
        mTvUpdate = findViewById(R.id.tv_update);

        mTvSend.setOnClickListener(this);
        mTvUpdate.setOnClickListener(this);
        findViewById(R.id.tv_create_handler).setOnClickListener(this);
        findViewById(R.id.tv_handler_thread).setOnClickListener(this);
        findViewById(R.id.thread_local).setOnClickListener(this);

        myHandler = new MyHandler(this);

        handlerThread = new HandlerThread("haha");
        handlerThread.start();
        mThreadHandler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                LogUtils.e("twj124", msg.what + "  " + Thread.currentThread().getName());
            }
        };

        mThreadHandler.sendEmptyMessage(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                //在子线程给handler发送数据
                mThreadHandler.sendEmptyMessage(2);
            }
        }).start();
    }

    private static class MyHandler extends Handler {

        //MyHandler 弱引用 HandlerActivity
        private WeakReference<HandlerActivity> mActivity;

        public MyHandler(HandlerActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_UPDATE:
                    mActivity.get().mTvSend.setText("haha2");
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_send:

                break;
            case R.id.tv_update:
                myHandler.sendEmptyMessage(MSG_UPDATE);
                break;
            case R.id.tv_create_handler:

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //需要加Looper.prepare(); 不然会在低版本上报错；高版本为啥不会？
                        Looper.prepare();
                        Handler handler = new Handler() {
                            @Override
                            public void handleMessage(Message msg) {
                                //super.handleMessage(msg);
                                LogUtils.e("twj124", "handleMessage");
                                Toast.makeText(getApplicationContext(), "handler msg", Toast.LENGTH_LONG).show();
                            }
                        };
                        handler.sendEmptyMessage(1);
                        //需要加Looper.loop() 需要放在发送消息之后
                        Looper.loop();

                        //方法二：获取主线程的looper，或者说是UI线程的looper
                       /* Handler handler2 = new Handler(Looper.getMainLooper()){
                            @Override
                            public void handleMessage(Message msg) {
                                //super.handleMessage(msg);
                                LogUtils.e("twj124", "handleMessage");
                                Toast.makeText(getApplicationContext(), "handler msg", Toast.LENGTH_LONG).show();
                            }
                        };
                        handler2.sendEmptyMessage(1);*/

                    }
                }).start();
                break;
            case R.id.tv_handler_thread:
                startActivity(new Intent(this, HandlerThreadActivity.class));
                break;
            case R.id.thread_local:
                doThreadLocal();
                break;
            default:
                break;
        }
    }

    private void doThreadLocal() {
        final ThreadLocal<String> threadLocal = new ThreadLocal<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("Thread 1");
                Log.e("twj", Thread.currentThread().getName() + "  " + threadLocal.get());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("Thread 2");
                Log.e("twj", Thread.currentThread().getName() + "  " + threadLocal.get());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("twj", Thread.currentThread().getName() + "  " + threadLocal.get());
            }
        }).start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
