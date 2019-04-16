package com.hust_twj.zademo.ui_params;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.hust_twj.zademo.utils.LogUtils;

/**
 * description ：
 * Created by Wenjing.Tang on 2019/4/9.
 */
public class TestTextView extends TextView {

    public TestTextView(Context context) {
        super(context);
    }

    public TestTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogUtils.e("twj124", "onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        LogUtils.e("twj124", "onLayout");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LogUtils.e("twj124", "onDraw");
    }

}
