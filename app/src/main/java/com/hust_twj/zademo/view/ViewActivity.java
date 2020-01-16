package com.hust_twj.zademo.view;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;

import com.hust_twj.zademo.R;

/**
 * Created by hust_twj
 * on 2019/2/26
 */
public class ViewActivity extends Activity {

    private CustomView mCustomView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

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
