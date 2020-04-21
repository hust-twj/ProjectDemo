package com.hust_twj.zademo.life_cycle;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * Description ：
 * Created by Wenjing.Tang on 2020/4/21.
 */
public class MyService extends Service {

    private static final String TAG = "twj124--" + MyService.class.getSimpleName();

//    public MyService(String name) {
//        super(name);
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate");

    }

//    @Override
//    public void onStart(@Nullable Intent intent, int startId) {
//        super.onStart(intent, startId);
//        Log.e(TAG, "onStart");
//
//    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");

        return super.onStartCommand(intent, flags, startId);
    }

//    @Override
//    protected void onHandleIntent(@Nullable Intent intent) {
//
//        Log.e(TAG, "onHandleIntent: " + Thread.currentThread().getName());
//
//    }

//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        Log.e(TAG, "onBind");
//
//        return super.onBind(intent);
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e(TAG, "onDestroy");

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        return null;
    }
}
