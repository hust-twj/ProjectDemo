package com.hust_twj.zademo.ui_widget.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * usage:
 * author: kHRYSTAL
 * create time: 16/10/24
 * update time:
 * email: 723526676@qq.com
 */

public class CircularHorizontalMode implements ItemViewMode {

    private int mCircleOffset = 500;
    private float mDegToRad = 1.0f / 180.0f * (float) Math.PI;
    private float mScalingRatio = 0.001f;
    private float mTranslationRatio = 0.15f;

    public CircularHorizontalMode() {

    }

    public CircularHorizontalMode(int circleOffset, float degToRad, float scalingRatio, float translationRatio) {
        mCircleOffset = circleOffset;
        mDegToRad = degToRad;
        mScalingRatio = scalingRatio;
        mTranslationRatio = translationRatio;
    }

    @Override
    public void applyToView(View v, RecyclerView parent) {
        float halfWidth = v.getWidth() * 0.5f;
        float parentHalfWidth = parent.getWidth() * 0.5f;
        float x = v.getX();
        float rot = parentHalfWidth - halfWidth - x;

        v.setPivotX(halfWidth);
        v.setPivotY(0.0f);
        v.setRotation(-rot * 0.05f);
        v.setTranslationY((float) (-Math.cos(rot * mTranslationRatio * mDegToRad) + 1) * mCircleOffset * 0.5f);

        float scale = 1.0f - Math.abs(parentHalfWidth - halfWidth - x) * mScalingRatio * 0.5f;
        v.setScaleX(scale);
        v.setScaleY(scale);
    }

}
