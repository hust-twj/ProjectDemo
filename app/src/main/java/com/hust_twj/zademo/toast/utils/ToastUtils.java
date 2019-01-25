package com.hust_twj.zademo.toast.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.widget.Toast;

import com.hust_twj.zademo.toast.MyToast;

/**
 * Created by Wenjing.Tang
 * on 2019/1/25
 */
public class ToastUtils {

    public ToastUtils() {
    }

    public static void toast(Context context, int strResId) {
        toast(context, strResId, MyToast.DURATION_SHORT);
    }

    public static void toast(Context context, CharSequence msg) {
        toast(context, msg, MyToast.DURATION_SHORT);
    }

    public static void toast(Context context, @StringRes int strResId, int time) {
        toast(context, context.getString(strResId), time);
    }

    public static void toast(Context context, CharSequence msg, int time) {
        if (context != null && msg != null && (!(context instanceof Activity) || !((Activity) context).isDestroyed())) {
            MyToast.make(context)
                    .setText(msg)
                    .setGravity(Gravity.CENTER, 0, 0)
                    .setDuration(changeTime(time))
                    .show();
        }
    }

    public static void toastFromBottom(Context context, CharSequence msg, int time) {
        if (context != null && msg != null && (!(context instanceof Activity) || !((Activity) context).isDestroyed())) {
            MyToast.make(context)
                    .setText(msg)
                    .setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 30)
                    .setDuration(changeTime(time))
                    .show();
        }
    }

    private static int changeTime(int time) {
        switch (time) {
            case Toast.LENGTH_SHORT:
                return MyToast.DURATION_SHORT;
            case Toast.LENGTH_LONG:
                return MyToast.DURATION_LONG;
            default:
                return time;
        }
    }

}
