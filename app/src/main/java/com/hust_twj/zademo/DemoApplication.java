package com.hust_twj.zademo;

import android.app.Application;
import android.content.Context;

import com.hust_twj.zademo.utils.CrashHandler;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Wenjing.Tang
 * on 2018/12/20
 */
public class DemoApplication extends Application {

    public static DemoApplication app;

    @Override
    public void onCreate() {
        super.onCreate();

        CrashHandler.getInstance().init(this);

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);


    }

    public static DemoApplication getApp() {
        return app;
    }

    public static Context getContext() {
        return app.getApplicationContext();
    }

}
