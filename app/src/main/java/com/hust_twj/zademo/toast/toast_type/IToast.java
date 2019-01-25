package com.hust_twj.zademo.toast.toast_type;

import android.view.View;

import com.hust_twj.zademo.toast.MyToast;

/**
 *
 * Toast抽象接口
 * Created by Wenjing.Tang
 * on 2019/1/25
 */
public interface IToast {

    int TOAST_DEFAULT_GRAVITY = 17;

    void show();

    void cancel();

    IToast setView(View view);

    View getView();

    IToast setDuration(@MyToast.Duration int duration);

    int getDuration();

    IToast setGravity(int gravity);

    int getGravity();

    IToast setGravity(int gravity, int xOffset, int yOffset);

    IToast setAnimation(int animation);

    int getAnimation();

    IToast setPriority(int priority);

    int getPriority();

    IToast setText(CharSequence charSequence);

    CharSequence getText();
}
