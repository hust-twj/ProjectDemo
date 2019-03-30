package com.hust_twj.zademo.leak_canary;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hust_twj.zademo.R;

/**
 * Created by Wenjing.Tang
 * on 2019/3/30
 */
public class HandlerLeakActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_leak_canary);


    }

}
