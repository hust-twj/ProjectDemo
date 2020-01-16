package com.hust_twj.zademo.ui_params;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.hust_twj.zademo.utils.LogUtils;

/**
 * description ：
 * Created by Wenjing.Tang on 2019/4/14.
 */
public class ActionChildView extends LinearLayout {

    private static final String TAG = "ActionView";

    public ActionChildView(Context context) {
        this(context, null);
    }

    public ActionChildView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ActionChildView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.e("", "child 1:onInterceptTouchEvent action:ACTION_DOWN");
                //return true;
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.e(TAG, "child 1:onInterceptTouchEvent action:ACTION_MOVE");
                return true;
            //break;
            case MotionEvent.ACTION_UP:
                LogUtils.e(TAG, "child 1:onInterceptTouchEvent action:ACTION_UP");
                //return true;
                break;
            case MotionEvent.ACTION_CANCEL:
                LogUtils.e(TAG, "child 1:onInterceptTouchEvent action:ACTION_CANCEL");
                break;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.e(TAG, "child 1:onTouchEvent action:ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.e(TAG, "child 1:onTouchEvent action:ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.e(TAG, "child 1:onTouchEvent action:ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                LogUtils.e(TAG, "child 1:onTouchEvent action:ACTION_CANCEL");
                break;
        }
        return true;
    }


}
