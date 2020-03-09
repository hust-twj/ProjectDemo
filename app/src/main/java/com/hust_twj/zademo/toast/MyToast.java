package com.hust_twj.zademo.toast;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.IntDef;
import androidx.core.app.NotificationManagerCompat;

import com.hust_twj.zademo.toast.toast_type.CustomToast;
import com.hust_twj.zademo.toast.toast_type.IToast;
import com.hust_twj.zademo.toast.toast_type.SystemToast;
import com.hust_twj.zademo.toast.utils.Util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Wenjing.Tang
 * on 2019/1/25
 */
public class MyToast {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({DURATION_SHORT, DURATION_LONG})
    public @interface Duration {
    }

    public static final int DURATION_SHORT = 2000;
    public static final int DURATION_LONG = 3500;

    public static IToast make(Context mContext) {
        if (mContext == null) {
            return null;
        }
        //如果有通知权限，直接使用系统Toast
        //MIUI系统没有通知权限时系统Toast也能正常展示
        if (NotificationManagerCompat.from(mContext).areNotificationsEnabled() || Util.isMIUI()) {
            return new SystemToast(mContext);
        } else {
            //否则使用自定义Toast
            return new CustomToast(mContext);
        }
    }

    /**
     * 终止并清除所有弹窗
     */
    public static void cancel() {
        CustomToast.cancelAll();
        SystemToast.cancelAll();
    }

    /**
     * 清除与{@param mActivity}关联的ActivityToast，避免窗口泄漏
     */
    public static void cancelActivityToast(Activity mActivity) {
        CustomToast.cancelActivityToast(mActivity);
    }

}
