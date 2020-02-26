package com.hust_twj.zademo.life_cycle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.hust_twj.zademo.R;

/**
 * Description ：应用生命周期
 * Created by Wenjing.Tang on 2020-2-24.
 */
public class ActivityLifeCycleActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        Log.e("twj124", "onCreate");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("twj124", "onResume");
    }

    /**
     * https://blog.csdn.net/fenggering/article/details/53907654
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("twj124", "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("twj124", "onRestoreInstanceState");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("twj124", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("twj124", "onDestroy");
    }

}
