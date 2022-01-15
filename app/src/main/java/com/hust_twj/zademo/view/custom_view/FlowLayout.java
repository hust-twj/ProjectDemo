package com.hust_twj.zademo.view.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * https://blog.nowcoder.net/n/fc1c3bec84e54247b6228850a56e7c89
 * Description ：标签布局
 * Created by hust_twj on 2022/1/15.
 */
public class FlowLayout extends ViewGroup {

    //二维数组：以行为单位保存所有的view
    private List<List<View>> allLines = new ArrayList<>();
    //每行的最大高度
    private List<Integer> heights = new ArrayList<>();
    //横向间距
    private int horizontalSpacing = 16;
    //纵向间距
    private int verticalSpacing = 8;

    //保存一行中的所有的view
    List<View> lineViews = new ArrayList<>();

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 1.自定义ViewGroup需要实现onMeasure()度量和onLayout()
     * 2.要测量自己的大小，需要先测量子view 的大小，所以需要先遍历子view，去measure
     *
     * @param widthMeasureSpec  widthMeasureSpec
     * @param heightMeasureSpec heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        clearMeasureParams();//内存 抖动

        int childCount = getChildCount();
        //先度量孩子
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        //解析父亲给ViewGroup的高度宽度
        int selfWidth = MeasureSpec.getSize(widthMeasureSpec);
        int selfHeight = MeasureSpec.getSize(heightMeasureSpec);
        //Log.d(TAG, "onMeasure: selfWidth = " + selfWidth + " ,selfHeight = " + selfHeight);

        //记录这行已经使用了多宽的size
        int lineWidthUsed = 0;
        // 一行的行高
        int lineHeight = 0;

        // measure过程中，子View要求的父ViewGroup的宽
        int parentNeededWidth = 0;
        // measure过程中，子View要求的父ViewGroup的高
        int parentNeededHeight = 0;

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            LayoutParams childLayoutParams = child.getLayoutParams();

            //3. 计算需要子view的宽高
            //而子view 的宽高是 根据 父view的MeasureSpec + 自己的layoutParams 计算出
            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, paddingLeft + paddingRight, childLayoutParams.width);
            int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, paddingTop + paddingBottom, childLayoutParams.height);
            //测量子view，这儿可以知道子view的宽高了
            child.measure(childWidthMeasureSpec, childHeightMeasureSpec);

            //获取到的 子View的度量宽高
            int childViewMeasuredWidth = child.getMeasuredWidth();
            int childViewMeasuredHeight = child.getMeasuredHeight();

            //一行放不下
            if (childViewMeasuredWidth + lineWidthUsed + horizontalSpacing > selfWidth) {
               //换行后，将 上一行的view 和 行高 保存下来
                allLines.add(lineViews);
                heights.add(lineHeight);

                //总的宽高
                parentNeededWidth = Math.max(parentNeededWidth, lineWidthUsed + heightMeasureSpec);
                parentNeededHeight = parentNeededHeight + lineHeight + verticalSpacing;

                //恢复初始化
                lineViews.clear();
                lineWidthUsed = 0;
                lineHeight = 0;
            }
            // view 是分行 layout的，所以要记录每一行有哪些view，这样可以方便layout布局
            lineViews.add(child);
            lineWidthUsed = lineWidthUsed + childViewMeasuredWidth + horizontalSpacing;
            lineHeight = Math.max(lineHeight, childViewMeasuredHeight);

            //处理最后一行数据
            if (i == childCount - 1) {
                allLines.add(lineViews);
                heights.add(lineHeight);
                parentNeededHeight = parentNeededHeight + lineHeight + verticalSpacing;
                parentNeededWidth = Math.max(parentNeededWidth, lineWidthUsed + horizontalSpacing);
            }

        }
        //父view MeasureSpec
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int realWidth = widthMode == MeasureSpec.EXACTLY ? selfWidth : parentNeededWidth;
        int realHeight = heightMode == MeasureSpec.EXACTLY ? selfHeight : parentNeededHeight;

        setMeasuredDimension(realWidth, realHeight);
    }

    private void clearMeasureParams() {
        allLines.clear();
        heights.clear();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (allLines.isEmpty()) {
            return;
        }
        int curL = getPaddingLeft(); //已经用掉的宽度
        int curT = getPaddingTop();//已经用掉的高度

        for (int i = 0; i < allLines.size(); i++) {
            List<View> lineViews = allLines.get(i);

            int lineHeight = heights.get(i);
            for (int j = 0; j < lineViews.size(); j++) { //本行view
                int left, top, right, bottom;
                View view = lineViews.get(j);
                left = curL;
                top = curT;
                right = left + view.getMeasuredWidth();
                bottom = top + view.getMeasuredHeight();
                //布局view
                view.layout(left, top, right, bottom);

                curL = right + horizontalSpacing; //下一个view左边距
            }
            curT = curT + lineHeight + verticalSpacing;
            curL = getPaddingLeft();
        }
    }
}
