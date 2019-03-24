package com.hust_twj.zademo.ui_widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hust_twj.zademo.R;


/**
 * 带渐变的环形进度条
 * Created by Wenjing.Tang
 * on 2019/3/8
 */
public class VoiceProgressRing extends View {

    private int progressStartColor;
    private int progressMidColor;
    private int progressEndColor;
    private int bgColor;
    private int dotColor;
    private float progress;
    private float progressWidth;//进度圆环的宽度
    private float bgWidth;//背景宽度
    private float dotWidth; //最后的小圆点的宽度

    private Paint bgPaint;
    private Paint progressPaint;
    private Paint dotPaint;

    private float mProgress = 0;

    private static final int MAX_PROGRESS = 100;
    private static final int SWEEP_ANGLE = 360;

    private int center;
    private float radius;

    /**
     * 弧度
     */
    private float mAngle;
    private Matrix matrix = new Matrix();


    private SweepGradient progressSeepGradient;

    public VoiceProgressRing(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.VoiceProgressRing);
        progressStartColor = ta.getColor(R.styleable.VoiceProgressRing_pr_progress_start_color, Color.YELLOW);
        progressMidColor = ta.getColor(R.styleable.VoiceProgressRing_pr_progress_mid_color, progressStartColor);
        progressEndColor = ta.getColor(R.styleable.VoiceProgressRing_pr_progress_end_color, progressStartColor);
        bgColor = ta.getColor(R.styleable.VoiceProgressRing_pr_bg_color, Color.LTGRAY);
        dotColor = ta.getColor(R.styleable.VoiceProgressRing_pr_dot_color, progressMidColor);
        progress = ta.getInt(R.styleable.VoiceProgressRing_pr_progress, 0);
        progressWidth = ta.getDimension(R.styleable.VoiceProgressRing_pr_progress_width, 2f);
        bgWidth = ta.getDimension(R.styleable.VoiceProgressRing_pr_bg_width, 1f);
        dotWidth = ta.getDimension(R.styleable.VoiceProgressRing_pr_dot_radius, 2.5f);
        ta.recycle();

        bgPaint = new Paint();
        bgPaint.setAntiAlias(true);
        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setStrokeWidth(bgWidth);
        bgPaint.setColor(bgColor);

        progressPaint = new Paint();
        progressPaint.setAntiAlias(true);
        progressPaint.setDither(true);
        progressPaint.setFilterBitmap(true);
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeWidth(progressWidth);

        dotPaint = new Paint();
        dotPaint.setAntiAlias(true);
        dotPaint.setStyle(Paint.Style.FILL);
        dotPaint.setStrokeWidth(dotWidth);
        dotPaint.setColor(dotColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        center = getWidth() / 2;
        radius = (center - bgWidth / 2);// 半径

        mProgress = progress;

        radius = radius - bgWidth;
        //画背景
        canvas.drawCircle(center, center, radius, bgPaint);

        drawProgress(canvas);
    }

    private RectF mRectF;

    /**
     * 画进度圆环
     *
     * @param canvas canvas
     */
    private void drawProgress(Canvas canvas) {

        if (mRectF == null) {
            mRectF = new RectF(center - radius, center - radius, center + radius, center + radius);//圆弧范围的外接矩形
        }

        int progressColorSweep[] = {progressStartColor, progressMidColor, progressEndColor};
        //设置渐变色
        if (progressSeepGradient == null) {
            progressSeepGradient = new SweepGradient(center, center, progressColorSweep, null);
        }
        //按照圆心旋转
        matrix.setRotate(-90, center, center);
        progressSeepGradient.setLocalMatrix(matrix);

        for (int i = 0; i <= mAngle; i++) {
            progressPaint.setShader(progressSeepGradient);
        }

        canvas.drawArc(mRectF, -90, mAngle, false, progressPaint);

        if (mProgress > 0 && mProgress < 100) {
            //平移画布之前保存之前画的
            canvas.save();
            // 将画布坐标原点移动至圆心
            canvas.translate(center, center);
            // 旋转和进度相同的角度,因为进度是从-90度开始的所以-90度
            canvas.rotate(mAngle - 90);
            // 同理从圆心出发直接将原点平移至要画小球的位置
            canvas.translate(radius, 0);
            canvas.drawCircle(0, 0, dotWidth, dotPaint);
            // 画完之后恢复画布坐标
            canvas.restore();
        }
    }

    /**
     * 设置进度
     *
     * @param progress 进度
     */
    public void setProgress(@FloatRange(from = 0f, to = 100f) float progress) {
        if (progress > MAX_PROGRESS) {
            this.progress = MAX_PROGRESS;
            mAngle = SWEEP_ANGLE;
        } else {
            this.progress = progress;
            mAngle = SWEEP_ANGLE * progress / MAX_PROGRESS;
        }
        invalidate();
    }

    public float getProgress() {
        return progress;
    }

}