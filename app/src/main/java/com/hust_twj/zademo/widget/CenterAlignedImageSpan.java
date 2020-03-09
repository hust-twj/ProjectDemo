package com.hust_twj.zademo.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import android.text.style.ImageSpan;

/**
 * 图片与文字居中对齐的ImageSpan
 * Created by wenjing.tang on 2018/9/18.
 *
 */
public class CenterAlignedImageSpan extends ImageSpan {

    public CenterAlignedImageSpan(Context context, Bitmap bitmap) {
        super(context, bitmap);

    }

    public CenterAlignedImageSpan(Context context, int drawableRes) {
        super(context, drawableRes);
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end,
                     float x, int top, int y, int bottom, @NonNull Paint paint) {

        Drawable drawable = getDrawable();
        Paint.FontMetricsInt fm = paint.getFontMetricsInt();
        //计算y方向的位移
        int transY = (y + fm.descent + y + fm.ascent) / 2 - drawable.getBounds().bottom / 2;
        canvas.save();
        //绘制图片位移一段距离
        canvas.translate(x, transY);
        drawable.draw(canvas);
        canvas.restore();
    }

}
