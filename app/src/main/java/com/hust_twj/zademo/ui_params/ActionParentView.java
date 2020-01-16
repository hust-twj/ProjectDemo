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
public class ActionParentView extends LinearLayout {

    private static final String TAG = "ActionView";

    public ActionParentView(Context context) {
        this(context, null);
    }

    public ActionParentView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ActionParentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.e("", "parent 1:onInterceptTouchEvent action:ACTION_DOWN");
                //return true;
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.e(TAG, "parent 1:onInterceptTouchEvent action:ACTION_MOVE");
                return true;
            //break;
            case MotionEvent.ACTION_UP:
                LogUtils.e(TAG, "parent 1:onInterceptTouchEvent action:ACTION_UP");
                //return true;
                break;
            case MotionEvent.ACTION_CANCEL:
                LogUtils.e(TAG, "parent 1:onInterceptTouchEvent action:ACTION_CANCEL");
                break;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.e(TAG, "parent 1:onTouchEvent action:ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.e(TAG, "parent 1:onTouchEvent action:ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.e(TAG, "parent 1:onTouchEvent action:ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                LogUtils.e(TAG, "parent 1:onTouchEvent action:ACTION_CANCEL");
                break;
        }
        return true;
    }


}
