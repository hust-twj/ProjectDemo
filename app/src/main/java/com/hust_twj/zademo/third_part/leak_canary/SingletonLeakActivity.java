package com.hust_twj.zademo.third_part.leak_canary;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hust_twj.zademo.R;

/**
 * 单例 内存泄露
 * Created by Wenjing.Tang
 * on 2019/8/5
 */
public class SingletonLeakActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_singleton_canary);

        CommonUtils.getInstance(this);

    }

}
