package com.hust_twj.zademo.view.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Description ：
 * Created by Wenjing.Tang on 2020/2/29.
 */
public class ViewGroupB extends LinearLayout {

    public ViewGroupB(Context context) {
        super(context);
    }

    public ViewGroupB(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroupB(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("twj124----->", "ViewGroupB dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("twj124----->", "ViewGroupB onInterceptTouchEvent");
        //return super.onInterceptTouchEvent(ev);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("twj124----->", "ViewGroupB onTouchEvent");
        //return super.onTouchEvent(event);
        return true;
    }
}
