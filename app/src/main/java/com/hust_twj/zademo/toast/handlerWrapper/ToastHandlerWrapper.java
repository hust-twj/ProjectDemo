package com.hust_twj.zademo.toast.handlerWrapper;

import android.os.Handler;
import android.os.Message;

/**
 * toast内部的handler的包装器
 * Created by Wenjing.Tang
 * on 2019/1/25
 */
public class ToastHandlerWrapper extends Handler {
    private Handler mHandler;

    public ToastHandlerWrapper(Handler handler) {
        mHandler = handler;
    }

    @Override
    public void dispatchMessage(Message msg) {
        try {
            mHandler.dispatchMessage(msg);
        } catch (Exception e) {
        }
    }

    @Override
    public void handleMessage(Message msg) {
        //需要委托给原Handler执行
        mHandler.handleMessage(msg);
    }
}