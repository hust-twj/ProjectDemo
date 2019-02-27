package com.hust_twj.zademo.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;


import com.hust_twj.zademo.R;

import java.lang.ref.WeakReference;

/**
 * PopupWindow基类
 *
 * @author hust_twj
 * @date 2019/2/23
 */

public abstract class BasePopupWindow extends PopupWindow implements PopupWindow.OnDismissListener {
    protected static final float ALPHA_HALF_TRANSPARENT = 0.7f;// 变暗，半透明
    protected static final float ALPHA_TRANSPARENT = 1.0f;// 全透明

    private Bundle mArgs;// 包含初始化参数

    private WeakReference<Context> mContext;
    private WeakReference<Window> mParentWindow;
    private boolean isShowShadow;
    private View mContentView;

    public BasePopupWindow(Activity activity) {
        this(activity, true);
    }

    public BasePopupWindow(Activity activity, boolean isShowShadow) {
        this(activity, true, false, null, isShowShadow);
    }

    public BasePopupWindow(Activity activity, Bundle args, boolean isShowShadow) {
        this(activity, true, false, args, isShowShadow);
    }

    public BasePopupWindow(Activity activity, boolean focusable,
                           boolean outsideTouchable, Bundle args, boolean showShadow) {
        super();
        this.isShowShadow = showShadow;
        mContext = new WeakReference<Context>(activity);
        mParentWindow = new WeakReference<>(activity.getWindow());
        mArgs = args;
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mContentView=LayoutInflater.from(activity).inflate(getLayoutId(), null);
        setContentView(mContentView);
        setWidth(overrideWidth());
        setHeight(overrideHeight());
        // 测量自身
        getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setTouchable(true);
        setFocusable(focusable);
        setOutsideTouchable(outsideTouchable);
        setOnDismissListener(this);
        init();
    }


    protected abstract int getLayoutId();

    protected int overrideWidth() {
        return ViewGroup.LayoutParams.MATCH_PARENT;
    }

    protected int overrideHeight() {
        return ViewGroup.LayoutParams.WRAP_CONTENT;
    }

    protected abstract void init();

    protected Context getContext() {
        return mContext.get();
    }

    protected Bundle getArgs() {
        return mArgs;
    }

    public void popupFromBottom() {
        if (getAnimationStyle() != R.style.BottomPopupWindow) {
            setAnimationStyle(R.style.BottomPopupWindow);
        }
        if(isShowShadow) {
            updateParentWindowAlpha(ALPHA_HALF_TRANSPARENT);
        }
        if (mParentWindow.get() != null) {
            showAtLocation(mParentWindow.get().getDecorView(), Gravity.BOTTOM, 0, 0);
        }
    }

    @Override
    public void onDismiss() {
        if(isShowShadow) {
            updateParentWindowAlpha(ALPHA_TRANSPARENT);
        }
    }

    protected void updateParentWindowAlpha(float alpha) {
        Window parentWindow = mParentWindow.get();
        if (parentWindow != null) {
            WindowManager.LayoutParams params = parentWindow.getAttributes();
            params.alpha = alpha;
            parentWindow.setAttributes(params);
        }
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T find(int id) {
        return (T) mContentView.findViewById(id);
    }


    protected Window getWindow() {
        return mParentWindow.get();
    }
}
