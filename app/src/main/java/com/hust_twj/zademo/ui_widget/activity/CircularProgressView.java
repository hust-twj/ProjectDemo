package com.hust_twj.zademo.ui_widget.activity;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.hust_twj.zademo.R;

/**
 * Created by Wenjing.Tang
 * on 2019/3/24
 */
public class CircularProgressView extends View {

    private Context mContext;
    private Paint mPaint;
    private int mProgress = 0;
    private static final int MAX_PROGRESS = 100;
    /**
     * 弧度
     */
    private int mAngle;
    /**
     * 内圆的颜色
     */
    private int inRoundColor;
    /**
     * 线的宽度
     */
    private int roundWidth;
    /**
     * 进度条颜色
     */
    private int progressBarColor;

    public CircularProgressView(Context context) {
        this(context, null);
    }

    public CircularProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircularProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init(attrs);
    }

    /**
     * 解析自定义属性
     *
     * @param attrs
     */
    public void init(AttributeSet attrs) {
        mPaint = new Paint();
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar);
        inRoundColor = typedArray.getColor(R.styleable.CircleProgressBar_inCircleColor, getResources().getColor(R.color.colorPrimaryDark));
        progressBarColor = typedArray.getColor(R.styleable.CircleProgressBar_progressColor, getResources().getColor(R.color.colorAccent));
        roundWidth = typedArray.getDimensionPixelOffset(R.styleable.CircleProgressBar_lineWidth, 20);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) { /** * 画外圆 */
        super.onDraw(canvas);
        int center = getWidth() / 2;//圆心
        int radius = (center - roundWidth / 2);// 半径

        mPaint.setStrokeWidth(roundWidth); //线的宽度
        mPaint.setStyle(Paint.Style.STROKE); //空心圆
        mPaint.setAntiAlias(true); //消除锯齿
        //canvas.drawCircle(center, center, radius, mPaint); //内圆

        mPaint.setColor(inRoundColor);
        radius = radius - roundWidth;
        canvas.drawCircle(center, center, radius, mPaint); //画进度是一个弧线

        mPaint.setColor(progressBarColor);
        RectF rectF = new RectF(center - radius, center - radius, center + radius, center + radius);//圆弧范围的外接矩形
        canvas.drawArc(rectF, -90, mAngle, false, mPaint);


        if (mProgress > 0 && mProgress < 100) {
            canvas.save(); //平移画布之前保存之前画的

            // 画进度终点的小球,旋转画布的方式实现
            mPaint.setStyle(Paint.Style.FILL);
            // 将画布坐标原点移动至圆心
            canvas.translate(center, center);
            // 旋转和进度相同的角度,因为进度是从-90度开始的所以-90度
            canvas.rotate(mAngle - 90);
            // 同理从圆心出发直接将原点平移至要画小球的位置
            canvas.translate(radius, 0);
            canvas.drawCircle(0, 0, roundWidth, mPaint);

            // 画完之后恢复画布坐标
            canvas.restore();
        }


    }


    public int getProgress() {
        return mProgress;
    }

    /**
     * 设置进度
     *
     * @return
     */
    public void setProgress(int p) {
        if (p > MAX_PROGRESS) {
            mProgress = MAX_PROGRESS;
            mAngle = 360;
        } else {
            mProgress = p;
            mAngle = 360 * p / MAX_PROGRESS;
        }
        //更新画布
        invalidate();
    }

}
