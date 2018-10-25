package com.hust_twj.zademo.frame_layout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.hust_twj.zademo.utils.DensityUtils;

public class FilletFrameLayout extends FrameLayout {

    public FilletFrameLayout(@NonNull Context context) {
        super(context);
    }

    public FilletFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FilletFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void draw(Canvas canvas) {
        Path path = new Path();
        path.addRoundRect(new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight()),
                DensityUtils.dp2px(getContext(),20),DensityUtils.dp2px(getContext(),20),
                Path.Direction.CW);
        canvas.clipPath(path, Region.Op.REPLACE);
        super.draw(canvas);
    }

}
