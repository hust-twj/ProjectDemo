package com.hust_twj.zademo.ui_widget.util;

import android.content.Context;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

/**
 * Description ：
 * Created by Wenjing.Tang on 2019-11-17.
 */
public class SelectBigPagerTitleView extends ColorTransitionPagerTitleView {

    public SelectBigPagerTitleView(Context context) {
        super(context);
    }
    @Override
    public void onSelected(int index, int totalCount) {
        setTextSize(16);
    }
    @Override
    public void onDeselected(int index, int totalCount) {
        setTextSize(14);
    }
}
