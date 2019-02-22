package com.hust_twj.zademo.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.LogUtils;


/**
 * Created by Wenjing.Tang
 * on 2019/2/18
 */
public class HandlerThreadActivity extends Activity implements View.OnClickListener {

    private TextView mTvSub2Main, mTvMain2Sub, mTvSub2Sub;
    private Handler mMainHandler, mSubHandler;
    private HandlerThread mHandlerThread;

    private static final int SUB_TO_MAIN = 1;
    private static final int MAIN_TO_SUB = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread);
        mTvSub2Main = findViewById(R.id.tv_handler_sub_to_main);
        mTvMain2Sub = findViewById(R.id.tv_handler_main_to_sub);
        mTvSub2Sub = findViewById(R.id.tv_handler_sub_to_sub);

        mTvSub2Main.setOnClickListener(this);
        mTvMain2Sub.setOnClickListener(this);
        mTvSub2Sub.setOnClickListener(this);

        mHandlerThread = new HandlerThread("handler_thread");
        mHandlerThread.start();
        mSubHandler = new Handler(mHandlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case SUB_TO_MAIN:
                        LogUtils.e("twj", msg.what + "  SubHandler 接收线程： " + Thread.currentThread().getName());
                        break;
                    case MAIN_TO_SUB:
                        LogUtils.e("twj", msg.what + "  SubHandler 接收线程： " + Thread.currentThread().getName());
                        break;
                }
            }
        };

        mMainHandler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case SUB_TO_MAIN:
                        LogUtils.e("twj", msg.what + "  MainHandler 接收线程： " + Thread.currentThread().getName());
                        break;
                    case MAIN_TO_SUB:
                        LogUtils.e("twj", msg.what + "  MainHandler 接收线程： " + Thread.currentThread().getName());
                        break;
                }
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_handler_sub_to_main:
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        LogUtils.e("twj", "发送线程： "+Thread.currentThread().getName());
                        mMainHandler.sendEmptyMessage(SUB_TO_MAIN);
                    }
                });
                thread.start();
                break;
            case R.id.tv_handler_main_to_sub:
                LogUtils.e("twj", "发送线程： "+Thread.currentThread().getName());
                mSubHandler.sendEmptyMessage(MAIN_TO_SUB);
                break;
            case R.id.tv_handler_sub_to_sub:
                Thread thread2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        LogUtils.e("twj", "发送线程： "+Thread.currentThread().getName());
                        mSubHandler.sendEmptyMessage(SUB_TO_MAIN);
                    }
                });
                thread2.start();
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //养成好习惯：在不需要HandlerThread的时候需要手动停止掉
        mHandlerThread.quit();
    }
}
