package com.hust_twj.zademo.moments_2_0.hot_topic.widget;

import android.content.Context;
import android.os.Build;
import com.google.android.material.appbar.AppBarLayout;
import android.util.AttributeSet;

/**
 * Created by XingjieZheng
 * on 2016/11/10.
 */

public class WithoutOutlineAppBarLayout extends AppBarLayout {


    public WithoutOutlineAppBarLayout(Context context) {
        this(context, null);
    }

    public WithoutOutlineAppBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        if (Build.VERSION.SDK_INT >= 21) {
            // remove the bounds view outline provider
            setOutlineProvider(null);
        }
    }


}
