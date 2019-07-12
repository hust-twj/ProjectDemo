package com.hust_twj.zademo.third_part.retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.LogUtils;

public class RetrofitActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_retrofit);

        AnnoationTest annoationTest = new AnnoationTest();
        LogUtils.e("twj124", annoationTest.getIp());

    }

}
