package com.hust_twj.zademo.ui_widget.widget;

import android.content.Context;

import androidx.recyclerview.widget.LinearSmoothScroller;

/**
 * Description ：
 * Created by Wenjing.Tang on 2020/3/11.
 */
public class TopSmoothScroller extends LinearSmoothScroller {

    public TopSmoothScroller(Context context) {
        super(context);
    }

    @Override
    protected int getHorizontalSnapPreference() {
        return SNAP_TO_START;//具体见源码注释
    }

    @Override
    protected int getVerticalSnapPreference() {
        return SNAP_TO_START;//具体见源码注释
    }

}
