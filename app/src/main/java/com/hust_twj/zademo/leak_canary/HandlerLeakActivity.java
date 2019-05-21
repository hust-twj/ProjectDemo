package com.hust_twj.zademo.leak_canary;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.hust_twj.zademo.R;

/**
 * Created by Wenjing.Tang
 * on 2019/3/30
 */
public class HandlerLeakActivity extends Activity {

    private Handler mHandler = new Handler();
    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_handler_leak);

        mTextView = findViewById(R.id.text);
        //模拟内存泄露
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mTextView.setText("twj");
            }
        }, 10 * 1000);

        finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mHandler.removeCallbacksAndMessages(null);
    }

}
