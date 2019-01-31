package com.hust_twj.zademo;

import android.app.Application;
import android.content.Context;

import com.hust_twj.zademo.utils.CrashHandler;

/**
 * Created by Wenjing.Tang
 * on 2018/12/20
 */
public class App extends Application {

    public static App app;

    @Override
    public void onCreate() {
        super.onCreate();

        CrashHandler.getInstance().init(this);
    }

    public static App getApp() {
        return app;
    }

    public static Context getContext() {
        return app.getApplicationContext();
    }

}
