package com.hust_twj.zademo.arch.life_cycle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hust_twj.zademo.R;

/**
 * Description ：
 * Created by Wenjing.Tang on 2019-08-07.
 */
public class LifeCycleActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);


        getLifecycle().addObserver(new MyLifeCycleObserver());
    }

}
