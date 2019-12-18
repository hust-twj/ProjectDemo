package com.hust_twj.zademo.ui_widget.activity;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.LogUtils;

/**
 * Created by hust_twj
 * on 2019/12/18
 *
 */
public class GradientDrawableActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gradient_drawable);

        TextView  textView = findViewById(R.id.tv_bg);
        LogUtils.e("twj12", textView.getBackground());

        GradientDrawable drawable = (GradientDrawable) textView.getBackground();
        drawable.getConstantState();
        //drawable.getOrientation();

        LogUtils.e("twj12", drawable.getOrientation());


        // textView.setText("haha");

    }

}
