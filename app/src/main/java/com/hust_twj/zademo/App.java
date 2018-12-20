package com.hust_twj.zademo;

import android.app.Application;

import com.hust_twj.zademo.utils.CrashHandler;

/**
 * Created by Wenjing.Tang
 * on 2018/12/20
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        CrashHandler.getInstance().init(this);
    }
}
