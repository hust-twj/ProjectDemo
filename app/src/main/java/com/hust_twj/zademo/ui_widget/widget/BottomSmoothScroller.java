package com.hust_twj.zademo.ui_widget.widget;

import android.content.Context;

import androidx.recyclerview.widget.LinearSmoothScroller;

/**
 * Description ：
 * Created by Wenjing.Tang on 2020/3/11.
 */
public class BottomSmoothScroller extends LinearSmoothScroller {

    public BottomSmoothScroller(Context context) {
        super(context);
    }

    @Override
    protected int getHorizontalSnapPreference() {
        return SNAP_TO_END;//具体见源码注释
    }

    @Override
    protected int getVerticalSnapPreference() {
        return SNAP_TO_END;//具体见源码注释
    }

}
