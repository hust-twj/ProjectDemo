package com.hust_twj.zademo.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.Nullable;

import com.hust_twj.zademo.R;

/**
 * View事件分发 结论：
 * 1. 整体事件分发呈现 U 形，从Activity开始把这个事件向下依次按照层级分发到最后的一个View或者ViewGroup，
 * 这个时候会执行最后一个View或者ViewGroup的onTouchEvent()方法，然后又向上依次按照层级去触发onTouchEvent() 事件，
 * 中途若没有被消费（返回true），就会传递直到activity，整个流程呈U形。
 * 2.
 * Created by hust_twj
 * on 2020/2/29
 */
public class ViewDispatchActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dispatch);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("twj124----->", "ViewDispatchActivity dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("twj124----->", "ViewDispatchActivity onTouchEvent");
        return super.onTouchEvent(event);
    }
}
