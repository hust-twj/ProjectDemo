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
public class LayoutC extends FrameLayout {

    private int rawX = 0;
    private int rawY = 0;
    private int width = 0;
    private int height = 0;
    private final String TAG = MainActivity.COMOMON_TAG + " " + LayoutC.class.getSimpleName();

    public LayoutC(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LayoutC(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //获取控件的 左上角 坐标
        post(new Runnable() {
            @Override
            public void run() {
                int[] location = new int[2];
                getLocationOnScreen(location);
                rawX = location[0];
                rawY = location[1];
                width = getWidth();
                height = getHeight();
                 Log.d(TAG, "isTouchPointInRect:" + "rawX:" + rawX + "  rawY:" + rawY + "  " + width + " " + " " + height
                         + " " +(rawX + width) + " " +(rawY + height));
            }
        });
    }

    // 触摸点是否在当前 view  方法内
    private boolean isTouchPointInRect(float rawX, float rawY) {
        Log.d(TAG, "isTouchPointInRect  " + rawX + " " + rawY);
        if (rawX >= this.rawX && rawX <= this.rawX + width &&
                rawY >= this.rawY && rawY <= this.rawY + height) {
            Log.d(TAG, "isTouchPointInRect  true ");
            return true;
        }
        Log.d(TAG, "isTouchPointInRect  false ");

        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "dispatchTouchEvent action:ACTION_DOWN");
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                //step 1：如果 触摸点 已经超出当前范围内，父 view 进行拦截
                //step2: 拦截MOVE, 见父控件
                if (!isTouchPointInRect(ev.getRawX(), ev.getRawY())) {
                    Log.d(TAG, "dispatchTouchEvent  如果 触摸点 已经超出当前View范围，父 view 进行拦截 ");
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
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
                break;
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
                break;
            case MotionEvent.ACTION_MOVE:

                Log.d(TAG, "onTouchEvent action:ACTION_MOVE" + " " + getWidth() + "  " + getHeight() + "  " + ev.getX() + " " + ev.getY() + " " + ev.getRawY());
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