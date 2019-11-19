package com.hust_twj.zademo.ui_widget.widget;


import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Bundle;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.BasePopupWindow;
import com.hust_twj.zademo.utils.LogUtils;

/**
 * Description ：
 * Created by Wenjing.Tang on 2019-11-19.
 */
public class LifecycleWindow extends BasePopupWindow implements LifecycleObserver {

    public LifecycleWindow(Activity activity) {
        super(activity);
    }

//    public LifecycleWindow(Activity activity, boolean isShowShadow) {
//        super(activity, isShowShadow);
//    }
//
//    public LifecycleWindow(Activity activity, Bundle args, boolean isShowShadow) {
//        super(activity, args, isShowShadow);
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_lifecycle_window;
    }

    @Override
    protected void init() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume() {

        LogUtils.e("twj124", "LifecycleWindow  onResume " + isShowing());
    }
}
