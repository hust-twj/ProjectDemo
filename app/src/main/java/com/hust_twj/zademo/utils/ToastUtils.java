package com.hust_twj.zademo.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Toast工具类
 */
public class ToastUtils {

    private static Toast sToast = null;
    private static Toast sToastFromBottom = null;

    /**
     * 显示一个Toast
     *
     * @param context  Context
     * @param strResId 字符串资源ID
     */
    public static void toast(Context context, int strResId) {
        toast(context, strResId, Toast.LENGTH_SHORT);
    }

    /**
     * 显示一个Toast
     *
     * @param context Context
     * @param msg     信息
     */
    public static void toast(Context context, CharSequence msg) {
        toast(context, msg, Toast.LENGTH_SHORT);
    }

    /**
     * 显示一个Toast
     *
     * @param context  Context
     * @param strResId 字符串资源ID
     * @param time     显示时间
     */
    public static void toast(Context context, @StringRes int strResId, int time) {
        toast(context, context.getString(strResId), time);
    }

    /**
     * 显示一个Toast
     *
     * @param context Context
     * @param msg     信息
     * @param time    显示时间
     */
    public static void toast(Context context, CharSequence msg, int time) {
        if (context == null ||
                ((context instanceof Activity) &&
                        ((Activity) context).isDestroyed())) {
            return;
        }
        if (sToast == null) {
            sToast = Toast.makeText(context.getApplicationContext(), msg, time);
            sToast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            sToast.setText(msg);
            sToast.setDuration(time);
            sToast.setGravity(Gravity.CENTER, 0, 0);
        }
        sToast.show();
    }

    /**
     * 显示一个Toast
     *
     * @param context Context
     * @param msg     信息
     * @param time    显示时间
     */
    public static void toastFromBottom(Context context, CharSequence msg, int time) {
        if (context == null ||
                ((context instanceof Activity) &&
                        ((Activity) context).isDestroyed())) {
            return;
        }
        if (sToastFromBottom == null) {
            sToastFromBottom = Toast.makeText(context.getApplicationContext(), msg, time);
            sToastFromBottom.setGravity(Gravity.BOTTOM, 0, 200);
        } else {
            sToastFromBottom.setText(msg);
            sToastFromBottom.setDuration(time);
            sToastFromBottom.setGravity(Gravity.BOTTOM, 0, 200);
        }
        sToastFromBottom.show();
    }
}
