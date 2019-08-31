package com.hust_twj.zademo.third_part.leak_canary;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.LogUtils;

/**
 * handler 内存泄露
 * Created by Wenjing.Tang
 * on 2019/8/5
 */
public class HandlerLeakActivity extends Activity {

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_handler_leak);

        //模拟内存泄露
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LogUtils.e("twj","haha");
            }
        }, 10 * 1000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mHandler.removeCallbacksAndMessages(null);
    }

}
