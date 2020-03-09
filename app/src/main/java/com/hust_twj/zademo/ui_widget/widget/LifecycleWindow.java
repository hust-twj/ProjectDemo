package com.hust_twj.zademo.ui_widget.widget;


import android.app.Activity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.PopupWindow;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.LogUtils;

import java.lang.ref.WeakReference;

/**
 * Description ：
 * Created by Wenjing.Tang on 2019-11-19.
 */
public class LifecycleWindow extends PopupWindow implements LifecycleObserver {

    private WeakReference<Window> mParentWindow;

    public LifecycleWindow(Activity activity) {
        super(activity);
        View contentView = LayoutInflater.from(activity).inflate(R.layout.layout_lifecycle_window, null);
        setContentView(contentView);
        mParentWindow = new WeakReference<>(activity.getWindow());
    }


    public void popupFromBottom() {
        if (mParentWindow.get() != null) {
            showAtLocation(mParentWindow.get().getDecorView(), Gravity.BOTTOM, 0, 0);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume() {
        if (isShowing()) {
            // TODO: 2019-11-22 refresh
        }

        LogUtils.e("twj124", "LifecycleWindow  onResume " + isShowing());
    }
}
