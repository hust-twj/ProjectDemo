package com.hust_twj.zademo.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.annotation.ColorInt;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hust_twj.zademo.R;


/**
 *
 * Created by Administrator on 2017/5/3.
 */

public class BaseTitleBar extends FrameLayout {
    private RelativeLayout leftLayout, rightLayout, middleLayout;
    private TextView titleTV, tvOperationRight;
    private ImageView leftImage, rightImage;
    private View titleBar, shadowView;

    public BaseTitleBar(Context context) {
        super(context);
        init();
    }

    public BaseTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.base_titlebar_layout, null);
        addView(view);
        initView(view);
    }

    private void initView(View view) {
        leftLayout =  view.findViewById(R.id.rl_back);
        rightLayout =  view.findViewById(R.id.rl_operation);
        middleLayout = view.findViewById(R.id.title_layout);
        titleTV = view.findViewById(R.id.tv_title);
        leftImage = view.findViewById(R.id.iv_back);
        rightImage = view.findViewById(R.id.iv_operation_icon);
        titleBar = view.findViewById(R.id.title_bar_layout);
        shadowView = view.findViewById(R.id.shadow_view);
        tvOperationRight = view.findViewById(R.id.tv_operation_right);
    }

    public void setShadowBackground(int resId) {
        shadowView.setBackgroundResource(resId);
    }

    public void hideShadow() {
        shadowView.setVisibility(GONE);
    }

    public void setTitleBarBackgroundColor(int color) {
        titleBar.setBackgroundColor(ContextCompat.getColor(getContext(), color));
    }

    public void setTitleBarBackground(int resId) {
        titleBar.setBackgroundResource(resId);
    }

    public void setTitleBarBackground(Drawable drawable) {
        titleBar.setBackground(drawable);
    }

    public View getTitleBar() {
        return titleBar;
    }

    public void setTitleTextColor(int color) {
        titleTV.setTextColor(ContextCompat.getColor(getContext(), color));
    }

    public void setLeftImage(int resId, View.OnClickListener onClickListener) {
        leftImage.setVisibility(VISIBLE);
        leftLayout.setVisibility(VISIBLE);
        leftImage.setBackgroundResource(resId);
        if (onClickListener != null)
            leftLayout.setOnClickListener(onClickListener);
    }

    public ImageView getLeftImage() {
        return leftImage;
    }

    public void hideLeftImage() {
        leftLayout.setVisibility(GONE);
    }

    public void setRightImage(int resId, View.OnClickListener onClickListener) {
        rightImage.setVisibility(VISIBLE);
        rightLayout.setVisibility(VISIBLE);
        rightImage.setBackgroundResource(resId);
        if (onClickListener != null)
            rightLayout.setOnClickListener(onClickListener);
    }

    public ImageView getRightImage() {
        return rightImage;
    }

    public void setLeftListener(View.OnClickListener onClickListener) {
        leftLayout.setVisibility(VISIBLE);
        if (onClickListener != null)
            leftLayout.setOnClickListener(onClickListener);
    }

    public void setRightListener(View.OnClickListener onClickListener) {
        rightLayout.setVisibility(VISIBLE);
        if (onClickListener != null)
            rightLayout.setOnClickListener(onClickListener);
    }

    public void hideRightLayout() {
        rightLayout.setVisibility(GONE);
    }

    public void showRightLayout() {
        rightLayout.setVisibility(VISIBLE);
    }

    public void setRightText(@StringRes int strId, View.OnClickListener onClickListener) {
        rightLayout.setVisibility(VISIBLE);
        tvOperationRight.setVisibility(View.VISIBLE);
        tvOperationRight.setText(strId);
        if (onClickListener != null) {
            rightLayout.setOnClickListener(onClickListener);
        }
    }

    public TextView getTitleTv() {
        return titleTV;
    }

    public RelativeLayout getTitleLayout() {
        return middleLayout;
    }

    public void setRightTextLP(RelativeLayout.LayoutParams params) {
        tvOperationRight.setLayoutParams(params);
    }

    public void setRightTextColor(@ColorInt int color) {
        tvOperationRight.setTextColor(color);
    }


    public void updateRightImage(int resId) {
        rightImage.setVisibility(VISIBLE);
        rightImage.setBackgroundResource(resId);
    }

    public void setTitleText(int resId) {
        setTitleText(getResources().getString(resId));
    }

    public void setTitleText(String title) {
        titleTV.setVisibility(VISIBLE);
        titleTV.setText(title);
    }


    public void addLeftLayout(View view) {
        leftLayout.setVisibility(VISIBLE);
        switchLayoutContent(leftLayout, view);
    }

    public void addMiddleLayout(View view) {
        switchLayoutContent(middleLayout, view);
    }

    public void addRightLayout(View view) {
        rightLayout.setVisibility(VISIBLE);
        switchLayoutContent(rightLayout, view);
    }

    public View getRightView() {
        return rightLayout;
    }

    private void switchLayoutContent(ViewGroup viewGroup, View view) {
        viewGroup.removeAllViews();
        viewGroup.addView(view);
    }
}
