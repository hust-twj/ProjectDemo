package com.hust_twj.zademo.ui_widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.LogUtils;

/**
 * 带渐变的进度条
 * Created by Wenjing.Tang
 * on 2019/3/8
 */
public class ProgressRing extends View {

    private int progressStartColor;
    private int progressEndColor;
    private int bgStartColor;
    //private int bgMidColor;
    private int bgEndColor;
    private int progress;
    private float progressWidth;//进度圆环的宽度
    private float bgWidth;//背景宽度
    private int startAngle;
    private int sweepAngle;
    private boolean showAnim;

    private int mMeasureHeight, mMeasureWidth;

    private Paint bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private Paint progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

    private RectF mProgressRectF;//背景的外接矩形
    private RectF mBgRect;//进度圆环的外接矩形

    private float unitAngle;
    private int curProgress = 0;

    public ProgressRing(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ProgressRing);
        progressStartColor = ta.getColor(R.styleable.ProgressRing_pr_progress_start_color, Color.YELLOW);
        progressEndColor = ta.getColor(R.styleable.ProgressRing_pr_progress_end_color, progressStartColor);
        bgStartColor = ta.getColor(R.styleable.ProgressRing_pr_bg_start_color, Color.LTGRAY);
       // bgMidColor = ta.getColor(R.styleable.ProgressRing_pr_bg_mid_color, bgStartColor);
        bgEndColor = ta.getColor(R.styleable.ProgressRing_pr_bg_end_color, bgStartColor);
        progress = ta.getInt(R.styleable.ProgressRing_pr_progress, 0);
        progressWidth = ta.getDimension(R.styleable.ProgressRing_pr_progress_width, 2f);
        bgWidth = ta.getDimension(R.styleable.ProgressRing_pr_bg_width, 1f);
        startAngle = ta.getInt(R.styleable.ProgressRing_pr_start_angle, -90);
        sweepAngle = ta.getInt(R.styleable.ProgressRing_pr_sweep_angle, 360);
        showAnim = ta.getBoolean(R.styleable.ProgressRing_pr_show_anim, true);
        ta.recycle();

        unitAngle = (float) (sweepAngle / 100.0);

        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setStrokeCap(Paint.Cap.ROUND);
        bgPaint.setStrokeWidth(bgWidth);

        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);
        progressPaint.setStrokeWidth(progressWidth);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mMeasureWidth = getMeasuredWidth();
        mMeasureHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //LogUtils.e("twj124", mMeasureWidth + "  " + mMeasureHeight + "  " + getWidth() );
        //LogUtils.e("twj124", getPaddingLeft() + "  " + getPaddingTop() + "  " + getPaddingRight() + "  " +getPaddingBottom());

        if (!showAnim) {
            curProgress = progress;
        }

        drawBg(canvas);
        drawProgress(canvas);

        if (curProgress < progress) {
            curProgress++;
            postInvalidate();
        }
    }

    /**
     * 画进度圆环之外的背景
     * @param canvas canvas
     */
    private void drawBg(Canvas canvas) {
        if (mBgRect == null) {
            float halfBgWidth = bgWidth / 2;
            mBgRect = new RectF(halfBgWidth + getPaddingLeft(), halfBgWidth + getPaddingTop(),
                    mMeasureWidth - halfBgWidth - getPaddingRight(),
                    mMeasureHeight - halfBgWidth - getPaddingBottom());
            LogUtils.e("twj124", halfBgWidth + "  " + halfBgWidth + "  " +
                    (mMeasureWidth - halfBgWidth) + "  " + (mMeasureHeight - halfBgWidth));

        }
        float halfSweep = sweepAngle / 2.0f;
        for (int i = sweepAngle, st = (int) (curProgress * unitAngle); i > st; --i) {
            if (i - halfSweep > 0) {
                bgPaint.setColor(getGradient((i - halfSweep) / halfSweep, bgStartColor, bgEndColor));
            } else {
                bgPaint.setColor(getGradient((halfSweep - i) / halfSweep, bgStartColor, bgStartColor));
            }
            canvas.drawArc(mBgRect, startAngle + i, 1, false, bgPaint);
        }
    }

    /**
     * 画进度圆环
     * @param canvas canvas
     */
    private void drawProgress(Canvas canvas) {
        if (mProgressRectF == null) {
            float halfProgressWidth = progressWidth / 2;
            mProgressRectF = new RectF(halfProgressWidth + getPaddingLeft(), halfProgressWidth + getPaddingTop(),
                    mMeasureWidth - halfProgressWidth - getPaddingRight(),
                    mMeasureHeight - halfProgressWidth - getPaddingBottom());
            LogUtils.e("twj124", halfProgressWidth + "  " + halfProgressWidth + "  " +
                    (mMeasureWidth - halfProgressWidth) + "  " + (mMeasureHeight - halfProgressWidth));

        }

        for (int i = 0, end = (int) (curProgress * unitAngle); i <= end; i++) {
            progressPaint.setColor(getGradient(i / (float) end, progressStartColor, progressEndColor));
            canvas.drawArc(mProgressRectF, startAngle + i, 1, false, progressPaint);
        }
    }

    /**
     * 设置进度
     * @param progress 进度
     */
    public void setProgress(@IntRange(from = 0, to = 100) int progress) {
        this.progress = progress;
        invalidate();
    }

    public int getProgress() {
        return progress;
    }

    public int getGradient(float fraction, int startColor, int endColor) {
        if (fraction > 1) fraction = 1;
        int alphaStart = Color.alpha(startColor);
        int redStart = Color.red(startColor);
        int blueStart = Color.blue(startColor);
        int greenStart = Color.green(startColor);
        int alphaEnd = Color.alpha(endColor);
        int redEnd = Color.red(endColor);
        int blueEnd = Color.blue(endColor);
        int greenEnd = Color.green(endColor);
        int alphaDifference = alphaEnd - alphaStart;
        int redDifference = redEnd - redStart;
        int blueDifference = blueEnd - blueStart;
        int greenDifference = greenEnd - greenStart;
        int alphaCurrent = (int) (alphaStart + fraction * alphaDifference);
        int redCurrent = (int) (redStart + fraction * redDifference);
        int blueCurrent = (int) (blueStart + fraction * blueDifference);
        int greenCurrent = (int) (greenStart + fraction * greenDifference);
        return Color.argb(alphaCurrent, redCurrent, greenCurrent, blueCurrent);
    }
}