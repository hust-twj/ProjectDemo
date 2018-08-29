package com.hust_twj.zademo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.LogUtils;

public class GridentTextView extends android.support.v7.widget.AppCompatTextView {

    private LinearGradient mLinearGradient;
    private Paint mPaint;
    private int mViewWidth = 0;
    private int mViewHeight = 0;
    private Rect mTextBound = new Rect();


    public GridentTextView(Context context) {
        this(context, null);
    }

    public GridentTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GridentTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();

    }

    private void init() {
        mPaint = getPaint();
        setTypeface(null, Typeface.BOLD_ITALIC);
       // setShadowLayer(20f,20f,20f, getResources().getColor(R.color.black));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        mViewWidth = getMeasuredWidth();
        mViewHeight = getMeasuredHeight();
        LogUtils.e("twj123", mViewWidth + "   " + mViewHeight);


        String mTipText = getText().toString();
        mPaint.getTextBounds(mTipText, 0, mTipText.length(), mTextBound);
        mLinearGradient = new LinearGradient(0, 0, mViewWidth, mViewHeight,
                new int[] {0xFFffe163, 0xFFffa200},
                null, Shader.TileMode.REPEAT);
        mPaint.setShader(mLinearGradient);
        canvas.drawText(mTipText, getMeasuredWidth() / 2 - mTextBound.width() / 2,
                getMeasuredHeight() / 2 +   mTextBound.height() / 2, mPaint);
         //setShadowLayer(20f,20f,20f, getResources().getColor(R.color.black));

    }

}
