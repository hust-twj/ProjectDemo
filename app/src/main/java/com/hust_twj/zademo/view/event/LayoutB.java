package com.hust_twj.zademo.view.event;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.hust_twj.zademo.MainActivity;

/**
 * @author hust_twj
 * @date 2021/2/22
 */
public class LayoutB extends FrameLayout {

    private final String TAG = MainActivity.COMOMON_TAG + " " + LayoutB.class.getSimpleName();

    public LayoutB(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LayoutB(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "dispatchTouchEvent action:ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "dispatchTouchEvent action:ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "dispatchTouchEvent action:ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "dispatchTouchEvent action:ACTION_CANCEL");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onInterceptTouchEvent action:ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:

                Log.d(TAG, "onInterceptTouchEvent action:ACTION_MOVE");
                //break;
                //step 2：父控件拦截 ACTION_MOVE
                return true;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onInterceptTouchEvent action:ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "onInterceptTouchEvent action:ACTION_CANCEL");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent action:ACTION_DOWN");
                //return false;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onTouchEvent action:ACTION_MOVE");
//                return false;
            break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent action:ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "onTouchEvent action:ACTION_CANCEL");
                break;
        }
        return super.onTouchEvent(ev);
    }

}