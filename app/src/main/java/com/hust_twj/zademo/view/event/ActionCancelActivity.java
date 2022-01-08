package com.hust_twj.zademo.view.event;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.Nullable;

import com.hust_twj.zademo.MainActivity;
import com.hust_twj.zademo.R;

/**
 * Cancel事件
 * Created by hust_twj
 * on 2022/1/7
 */
public class ActionCancelActivity extends Activity {

    private final String TAG = MainActivity.COMOMON_TAG + " " + ActionCancelActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_cancel);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "ActionCancelActivity dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "ActionCancelActivity onTouchEvent");
        return super.onTouchEvent(event);
    }

}
