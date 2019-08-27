package com.hust_twj.zademo.utils;

import android.util.Log;
import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Description ：
 * Created by Wenjing.Tang on 2019-08-23.
 */
public class ClickUtils {

    public static void preventRepeatedClick(final View target, final View.OnClickListener listener) {

        RxView.clicks(target)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        listener.onClick(target);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    // 两次点击间隔不能少于1000ms
    private static final int FAST_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = true;

        long currentClickTime = System.currentTimeMillis();
        Log.e("twj126", currentClickTime + "   " + lastClickTime + "");
        if ((currentClickTime - lastClickTime) >= FAST_CLICK_DELAY_TIME) {
            flag = false;
        }
        lastClickTime = currentClickTime;
        // Log.e("twj125",lastClickTime +"");
        return flag;
    }

}
