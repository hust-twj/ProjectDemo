package com.hust_twj.zademo.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.view.custom_view.CustomView;

/**
 * Created by hust_twj
 * on 2019/2/26
 */
public class ViewRequestLayoutActivity extends Activity {

    private CustomView mCustomView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_request_layout);

        mCustomView = findViewById(R.id.custom_view);
    }

    //invalidate 走 onMeasure()
    public void invalidate(View view) {
        mCustomView.invalidate();
    }

    //requestLayout 走 onMeasure() --> onLayout()
    public void requestLayout(View view) {
        mCustomView.requestLayout();
    }

}
