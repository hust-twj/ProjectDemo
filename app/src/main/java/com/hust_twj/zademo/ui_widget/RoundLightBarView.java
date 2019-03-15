package com.hust_twj.zademo.ui_widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.DensityUtils;
import com.hust_twj.zademo.utils.LogUtils;

/**
 * Created by Wenjing.Tang
 * on 2019/3/14
 */
public class RoundLightBarView extends View {
    private int mTotalWidth, mTotalHeight;
    private int mCenterX, mCenterY;
    //底色画笔
    private Paint mCirclePaint;
    //进度条画笔
    private Paint mProgressPaint;
    // 矩阵,用于对图片进行一些操作
    private Matrix mMatrix;
    // 当前点的实际位置
    private float[] pos;
    // 当前点的tangent值,用于计算图片所需旋转的角度
    private float[] tan;

    private int mCircleR;

    private Paint mBitmapPaint;

    private Context mContext;
    //距离外围的边距
    private float interval;

    // 圆点图片
    private Bitmap mLittleBitmap;

    private int curProgress = 0;
    private int sweepAngle = 360;
    private float unitAngle = (float) (sweepAngle / 100.0);

    public RoundLightBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public RoundLightBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        interval = DensityUtils.px2dp(mContext, 50);
        initPaint();
        initBitmap();
    }

    private void initBitmap() {
        mMatrix = new Matrix();
        pos = new float[2];
        tan = new float[2];

        mLittleBitmap = getBitmapFromDrawable(getResources().getDrawable(R.drawable.bg_pain_round));
    }

    private Bitmap getBitmapFromDrawable(@NonNull Drawable drawable) {
        final Bitmap bmp = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bmp);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bmp;
    }

    private void initPaint() {
        //画黑底的深色圆画笔
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setColor(Color.parseColor("#0f221C33"));
        mCirclePaint.setStyle(Paint.Style.STROKE);
        //圆半径
        mCircleR = DensityUtils.px2dp(mContext, 10);
        mCirclePaint.setStrokeWidth(mCircleR);

        //画彩色圆弧的画笔
        mProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mProgressPaint.setStyle(Paint.Style.STROKE);
        //设置笔刷样式为原型
        mProgressPaint.setStrokeCap(Paint.Cap.ROUND);
        //设置圆弧粗
        mProgressPaint.setStrokeWidth(mCircleR);

        //圆点画笔
        mBitmapPaint = new Paint();
        mBitmapPaint.setStyle(Paint.Style.FILL);
        mBitmapPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.setDrawFilter(
                new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        int startAngle = -90;
        canvas.drawCircle(mCenterX, mCenterY, mCenterX - mCircleR - interval, mCirclePaint);
        //画进度条
        int colorSweep[] = {0xFFA686FF,0xFFF794BF};

        //设置渐变色
        SweepGradient sweepGradient = new SweepGradient(mCenterX, mCenterY, colorSweep, null);
        //按照圆心旋转
        Matrix matrix = new Matrix();
        matrix.setRotate(startAngle, mCenterX, mCenterY);
        sweepGradient.setLocalMatrix(matrix);

        RectF mProgressRectF = new RectF(0 + mCircleR + interval, 0 + mCircleR + interval,
                mTotalWidth - mCircleR - interval, mTotalHeight - mCircleR - interval);

        for (int i = 0, end = (int) (curProgress * unitAngle); i <= end; i++) {
            mProgressPaint.setShader(sweepGradient);
            canvas.drawArc(mProgressRectF, startAngle, end, false, mProgressPaint);
        }

        //绘制白色小星星
        Path orbit = new Path();
        for (int i = 0, end = (int) (curProgress * unitAngle); i <= end; i++) {
            orbit.addArc(new RectF(0 + mCircleR + interval, 0 + mCircleR + interval,
                            mTotalWidth - mCircleR - interval, mTotalHeight - mCircleR - interval)
                    , end + startAngle, 1);
        }

        LogUtils.e("twj124", mTotalWidth -2* mCircleR -2*  interval);

        //创建 PathMeasure
        PathMeasure measure = new PathMeasure(orbit, false);
        measure.getPosTan(measure.getLength() * 1, pos, tan);
        mMatrix.reset();

        if (curProgress == 0) {
            mMatrix.postScale(0, 0);
        } else {
            mMatrix.postScale(0.8f, 0.8f);
        }
        // 将图片绘制中心调整到与当前点重合
        mMatrix.postTranslate(pos[0] - mLittleBitmap.getWidth(), pos[1] - mLittleBitmap.getHeight());

        //绘制球
        canvas.drawBitmap(mLittleBitmap, mMatrix, mBitmapPaint);
    }

    /**
     * 设置进度
     *
     * @param progress 进度
     */
    public void setProgress(@IntRange(from = 0, to = 100) int progress) {
        this.curProgress = progress;
        invalidate();
    }

    public int getProgress() {
        return curProgress;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTotalWidth = w;
        mTotalHeight = h;
        mCenterX = mTotalWidth / 2;
        mCenterY = mTotalHeight / 2;
    }

}
