package com.hust_twj.zademo.ui_widget.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.hust_twj.zademo.utils.DensityUtils;


/**
 * description ：圆形列表
 * Created by Wenjing.Tang on 2019-06-02.
 */
public class CircleRecyclerView extends RecyclerView {

    private boolean mNeedLoop = true;
    private boolean mFirstOnLayout;

    /**
     * 背景相关
     */
    private boolean mDrawBg = false;
    private Paint mPaint;
    private RectF topArc, bottomArc;
    //白色背景圆的半径
    private int BG_RADIUS = DensityUtils.dp2px(getContext(), 500);

    public CircleRecyclerView(Context context) {
        this(context, null);
    }

    public CircleRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOverScrollMode(OVER_SCROLL_NEVER);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(DensityUtils.dp2px(context, 2));
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (mNeedLoop) {
            if (!mFirstOnLayout) {
                mFirstOnLayout = true;
            }
        } else {
            setClipToPadding(false);
            setClipChildren(false);
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        final int count = getChildCount();
        for (int i = 0; i < count; ++i) {
            View v = getChildAt(i);
            applyToView(v, this);
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制白色背景
        if (mDrawBg) {
            ViewGroup centerView = null;
            int imageHeight = 0;
            if (topArc == null) {
                centerView = (ViewGroup) findViewAtCenter();
                if (centerView == null) {
                    return;
                }
                View imageView = centerView.getChildAt(0);
                if (imageView == null) {
                    return;
                }
                imageHeight = imageView.getHeight();
                //图片中心点的坐标
                int top = (centerView.getTop() + imageHeight) / 2;
                topArc = new RectF(getWidth() / 2f - BG_RADIUS, top,
                        getWidth() / 2f + BG_RADIUS, top + BG_RADIUS);
            }

            if (bottomArc == null && centerView != null) {
                int top = centerView.getBottom() + imageHeight / 2 + DensityUtils.dp2px(getContext(), 20);
                bottomArc = new RectF(getWidth() / 2f - BG_RADIUS, top,
                        getWidth() / 2f + BG_RADIUS, top + BG_RADIUS);
            }
            canvas.drawArc(topArc, -180, 180, false, mPaint);
            canvas.drawArc(bottomArc, -180, 180, false, mPaint);
        }

    }

    @Override
    public void requestLayout() {
        super.requestLayout();
        if (getLayoutManager() == null) {
            return;
        }
        int count = getLayoutManager().getChildCount();
        for (int i = 0; i < count; ++i) {
            View v = getChildAt(i);
            applyToView(v, this);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                performClick();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
            default:
                break;
        }
        return super.onTouchEvent(e);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    public View findViewAt(int x, int y) {
        final int count = getChildCount();
        for (int i = 0; i < count; ++i) {
            final View v = getChildAt(i);
            final int x0 = v.getLeft();
            final int y0 = v.getTop();
            final int x1 = v.getWidth() + x0;
            final int y1 = v.getHeight() + y0;
            if (x >= x0 && x <= x1 && y >= y0 && y <= y1) {
                return v;
            }
        }
        return null;
    }

    public View findViewAtCenter() {
        View view = null;
        if (getLayoutManager().canScrollVertically()) {
            view = findViewAt(0, getHeight() / 2);
        } else if (getLayoutManager().canScrollHorizontally()) {
            view = findViewAt(getWidth() / 2, 0);
        }
        if (view != null) {
            return view;
        }

        //可能找不到中心，容错处理，最多尝试五次
        for (int i = 1; i <= 5; i++) {
            if (getLayoutManager().canScrollVertically()) {
                view = findViewAt(0, getHeight() / getChildCount() * i + getHeight() / 2);
            } else if (getLayoutManager().canScrollHorizontally()) {
                view = findViewAt(getWidth() / getChildCount() * i + getWidth() / 2, 0);
            }
            if (view != null) {
                return view;
            }
        }
        return null;
    }

    /**
     * 是否循环
     *
     * @param needLoop 默认true
     */
    public void setNeedLoop(boolean needLoop) {
        mNeedLoop = needLoop;
    }

    /**
     * 是否需要绘制背景
     *
     * @param drawBg true：需要绘制背景
     */
    public void setDrawBg(boolean drawBg) {
        mDrawBg = drawBg;
    }

    public void applyToView(View v, RecyclerView parent) {

        int mCircleOffset = 500;
        float mDegToRad = 1.0f / 180.0f * (float) Math.PI;
        float mTranslationRatio = 0.15f;

        float halfWidth = v.getWidth() * 0.5f;
        float parentHalfWidth = parent.getWidth() * 0.5f;
        float x = v.getX();
        float rot = parentHalfWidth - halfWidth - x;

        v.setPivotX(halfWidth);
        v.setPivotY(0.0f);
        //v.setRotation(-rot * 0.05f);
        v.setTranslationY((float) (-Math.cos(rot * mTranslationRatio * mDegToRad) + 1) * mCircleOffset * 0.15f);
    }

}
