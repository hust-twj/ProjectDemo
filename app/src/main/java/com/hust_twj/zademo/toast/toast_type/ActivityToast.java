package com.hust_twj.zademo.toast.toast_type;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.WindowManager;

import org.jetbrains.annotations.NotNull;

/**
 *
 * 只展示在当前Activity的弹窗，不具有跨页面功能, 销毁Activity时需要主动清除相应的ActivityToast, 否则会造成窗口泄漏：/has leaked window/。
 * 在某些手机上，如小米MAX(MIUI10 8.8.2开发版)，使用ApplicationContext创建WindowManager去展示TYPE_TOAST时会无法展示且addView()不会抛出异常，
 * 使用ActivityContext则能正常显示。
 *
 * 缺点：ActivityToast无法在保证不泄漏窗口的情况下跨Activity展示
 *
 * Created by Wenjing.Tang
 * on 2019/1/25
 */
public class ActivityToast extends CustomToast {

    public ActivityToast(@NotNull Context context) {
        //context非Activity时会抛出异常:Unable to add window -- token null is not valid; is your activity running?
        super(context);
    }

    @Override
    public WindowManager.LayoutParams getWMParams() {
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        lp.format = PixelFormat.TRANSLUCENT;

        lp.type = WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG;

        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.windowAnimations = android.R.style.Animation_Toast;
        // TODO: 2018/11/20 考虑是否需要引入windowToken
//        lp.token=((Activity)getContext()).getWindow().getDecorView().getWindowToken();
        lp.gravity = getGravity();
        lp.x = getXOffset();
        lp.y = getYOffset();
        return lp;
    }

    @Override
    public WindowManager getWindwoManager() {
        //context非Activity时会抛出异常:Unable to add window -- token null is not valid; is your activity running?
        if (mContext instanceof Activity) {
            return ((Activity) mContext).getWindowManager();
        } else {
            return null;
        }
    }
}

