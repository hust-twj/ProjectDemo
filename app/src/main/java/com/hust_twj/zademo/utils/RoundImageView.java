package com.hust_twj.zademo.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.hust_twj.zademo.R;


/**
 * 圆角imageview
 * @author wensibob
 * on 2018/5/23.
 */
public class RoundImageView extends AppCompatImageView {
    /**
     * 图片的类型，圆形or圆角
     */
    private int type;
    public static final int TYPE_CIRCLE = 0;
    public static final int TYPE_ROUND = 1;
    public static final int TYPE_ROUND_DIFF_RADIUS = 2;
    private static final String STATE_INSTANCE = "state_instance";
    private static final String STATE_TYPE = "state_type";
    private static final String STATE_BORDER_RADIUS = "state_border_radius";
    private static final String STATE_BORDER_RADIUS_ARRAY = "STATE_BORDER_RADIUS_ARRAY";
    /**
     * 圆角大小的默认值
     */
    private static final int BODER_RADIUS_DEFAULT = 10;
    /**
     * 圆角的大小
     */
    private int mBorderRadius;
    private float[] mBorderRadiusArray;
    private Path mPath;

    private Bitmap mBitmap;
    private Canvas mCanvas;
    /**
     * 绘图的Paint
     */
    private Paint mBitmapPaint;
    /**
     * 圆角的半径
     */
    private int mRadius;
    /**
     * 3x3 矩阵，主要用于缩小放大
     */
    private Matrix mMatrix;

    /**
     * 渲染图像，使用图像为绘制图形着色
     */
    private BitmapShader mBitmapShader;
    /**
     * view的宽度
     */
    private int mWidth;
    private RectF mRoundRect;

    public RoundImageView(Context context, AttributeSet attrs) {

        super(context, attrs);
        mMatrix = new Matrix();
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.RoundImageView);

        // 默认为10dp
        mBorderRadius = a.getDimensionPixelSize(R.styleable.RoundImageView_borderRadius,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, BODER_RADIUS_DEFAULT,
                        getResources().getDisplayMetrics()));
        // 默认为10dp
        int radiusLeftTop = a.getDimensionPixelSize(R.styleable.RoundImageView_radius_left_up,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0f,
                        getResources().getDisplayMetrics()));
        // 默认为10dp
        int radiusRightTop = a.getDimensionPixelSize(R.styleable.RoundImageView_radius_right_up,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0f,
                        getResources().getDisplayMetrics()));
        // 默认为10dp
        int radiusLeftBottom = a.getDimensionPixelSize(R.styleable.RoundImageView_radius_left_down,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0f,
                        getResources().getDisplayMetrics()));
        // 默认为10dp
        int radiusRightBottom = a.getDimensionPixelSize(R.styleable.RoundImageView_radius_right_down,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0f,
                        getResources().getDisplayMetrics()));
        // 默认为Circle
        type = a.getInt(R.styleable.RoundImageView_roundImageType, TYPE_CIRCLE);
        mBorderRadiusArray = getRadiusArray(radiusLeftTop, radiusRightTop,
                radiusRightBottom, radiusLeftBottom);
        a.recycle();
    }

    public RoundImageView(Context context) {
        this(context, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        /**
         * 如果类型是圆形，则强制改变view的宽高一致，以小值为准
         */
        if (type == TYPE_CIRCLE) {
            mWidth = Math.min(getMeasuredWidth(), getMeasuredHeight());
            mRadius = mWidth / 2;
            setMeasuredDimension(mWidth, mWidth);
        }

    }

    /**
     * 初始化BitmapShader
     */
    private void setUpShader() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }

        Bitmap bmp = drawableToBitamp(drawable);
        // 将bmp作为着色器，就是在指定区域内绘制bmp
        mBitmapShader = new BitmapShader(bmp, TileMode.CLAMP, TileMode.CLAMP);
        float scale = 1.0f;
        if (type == TYPE_CIRCLE) {
            // 拿到bitmap宽或高的小值
            int bSize = Math.min(bmp.getWidth(), bmp.getHeight());
            scale = mWidth * 1.0f / bSize;

        } else if (type == TYPE_ROUND) {
            if (!(bmp.getWidth() == getWidth() && bmp.getHeight() == getHeight())) {
                // 如果图片的宽或者高与view的宽高不匹配，计算出需要缩放的比例；缩放后的图片的宽高，一定要大于我们view的宽高；所以我们这里取大值；
                scale = Math.max(getWidth() * 1.0f / bmp.getWidth(),
                        getHeight() * 1.0f / bmp.getHeight());
            }

        }
        // shader的变换矩阵，我们这里主要用于放大或者缩小
        mMatrix.setScale(scale, scale);
        // 设置变换矩阵
        mBitmapShader.setLocalMatrix(mMatrix);
        // 设置shader
        mBitmapPaint.setShader(mBitmapShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null) {
            return;
        }
        setUpShader();

        if (type == TYPE_ROUND) {
            if (hasDifferentRadius()) {
                if (mPath == null) {
                    mPath = new Path();
                }
                resetPath(mPath, mBorderRadiusArray);
                canvas.drawPath(mPath, mBitmapPaint);
            } else {
                canvas.drawRoundRect(mRoundRect, mBorderRadius, mBorderRadius, mBitmapPaint);
            }
        } else {
            canvas.drawCircle(mRadius, mRadius, mRadius, mBitmapPaint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // 圆角图片的范围
        if (type == TYPE_ROUND) {
            mRoundRect = new RectF(0, 0, w, h);
        }
    }

    /**
     * drawable转bitmap
     *
     * @param drawable
     * @return
     */
    private Bitmap drawableToBitamp(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }
        int w = getMeasuredWidth();
        int h = getMeasuredHeight();
        if (w < 0 || w == 0) {
            w = (int) getResources().getDimension(R.dimen.avatar_img_max_dimen);
        }

        if (h < 0 || h == 0) {
            h = (int) getResources().getDimension(R.dimen.avatar_img_max_dimen);
        }

        if (mBitmap == null) {
            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_4444);
        }
        if (mCanvas == null) {
            mCanvas = new Canvas(mBitmap);
        }
        drawable.setBounds(0, 0, w, h);
        drawable.draw(mCanvas);
        return mBitmap;
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(STATE_INSTANCE, super.onSaveInstanceState());
        bundle.putInt(STATE_TYPE, type);
        bundle.putInt(STATE_BORDER_RADIUS, mBorderRadius);
        bundle.putFloatArray(STATE_BORDER_RADIUS_ARRAY, mBorderRadiusArray);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            super.onRestoreInstanceState(((Bundle) state)
                    .getParcelable(STATE_INSTANCE));
            this.type = bundle.getInt(STATE_TYPE);
            this.mBorderRadius = bundle.getInt(STATE_BORDER_RADIUS);
            this.mBorderRadiusArray = bundle.getFloatArray(STATE_BORDER_RADIUS_ARRAY);
        } else {
            super.onRestoreInstanceState(state);
        }

    }

    public void setBorderRadius(int borderRadius) {
        int pxVal = dp2px(borderRadius);
        if (this.mBorderRadius != pxVal) {
            this.mBorderRadius = pxVal;
            invalidate();
        }
    }

    public void setBorderRadius(int radiusLeftTop, int radiusRightTop,
                                int radiusLeftBottom, int radiusRightBottom) {
        mBorderRadiusArray = getRadiusArray(dp2px(radiusLeftTop), dp2px(radiusRightTop),
                dp2px(radiusLeftBottom), dp2px(radiusRightBottom));
        invalidate();
    }

    public void setType(int type) {
        if (this.type != type) {
            this.type = type;
            if (this.type != TYPE_ROUND && this.type != TYPE_CIRCLE) {
                this.type = TYPE_CIRCLE;
            }
            requestLayout();
        }

    }

    public int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }

    private float[] getRadiusArray(int lt, int rt, int rb, int lb) {
        return new float[] {lt, lt, rt, rt, rb, rb, lb, lb};
    }

    private boolean hasDifferentRadius() {
        if (mBorderRadiusArray != null) {
            for (float radius : mBorderRadiusArray) {
                if (radius > 0f) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mCanvas != null) {
            mCanvas = null;
        }
        if (mBitmap != null) {
            if (!mBitmap.isRecycled()) {
                mBitmap.recycle();
            }
            mBitmap = null;
        }
    }

    private void resetPath(Path path, float[] radiusArray) {
        path.reset();
        path.addRoundRect(mRoundRect, radiusArray, Path.Direction.CW);
    }
}
