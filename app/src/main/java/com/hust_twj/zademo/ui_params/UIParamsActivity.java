package com.hust_twj.zademo.ui_params;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.LogUtils;

public class UIParamsActivity extends Activity {

    private TextView mTvUIParams;
    private TextView mTvScrollTo;
    private TextView mTvScrollBy;
    private int mScrollX = -20;
    private int mScrollY = -20;

    private TextView mTvAction;
    private ActionParentView mActionView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ui_params);

        mTvUIParams = findViewById(R.id.tv_ui_params);
        mTvScrollTo = findViewById(R.id.tv_scroll_to);
        mTvScrollBy = findViewById(R.id.tv_scroll_by);
        mTvAction = findViewById(R.id.action_down_cancel);
        mActionView = findViewById(R.id.view_action);

        mTvUIParams.post(new Runnable() {
            @Override
            public void run() {
                LogUtils.e("twj124", mTvUIParams.getLeft() + "  " + mTvUIParams.getTop() +
                        "  " + mTvUIParams.getRight() + "  " + mTvUIParams.getBottom() + "  " +
                        mTvUIParams.getX() + "  " + mTvUIParams.getTranslationX());
            }
        });

        mTvAction.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        LogUtils.e("twj124", "ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        LogUtils.e("twj124", "ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        LogUtils.e("twj124", "ACTION_UP");
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        LogUtils.e("twj124", "ACTION_CANCEL");
                        break;
                }
                return false;
            }
        });

    }

    public void scratchCard(View view){
       doObjectAnimation();
       //doViewAnimation();
    }

    private void doObjectAnimation(){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTvUIParams, "translationX", mTvUIParams.getX(), 300);

        objectAnimator.setDuration(3000);
        objectAnimator.start();
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //60  60  360  360  360.0  300.0
                LogUtils.e("twj124", "end  "+  mTvUIParams.getLeft() + "  " + mTvUIParams.getTop() +
                        "  " + mTvUIParams.getRight() + "  " + mTvUIParams.getBottom() + "  " +
                        mTvUIParams.getX() + "  " + mTvUIParams.getTranslationX());

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void doViewAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0);
        translateAnimation.setDuration(3000);
        mTvUIParams.startAnimation(translateAnimation);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // 60  60  360  360  60.0  0.0
                LogUtils.e("twj124", "end  "+  mTvUIParams.getLeft() + "  " + mTvUIParams.getTop() +
                        "  " + mTvUIParams.getRight() + "  " + mTvUIParams.getBottom() + "  " +
                        mTvUIParams.getX() + "  " + mTvUIParams.getTranslationX());
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void scrollTo(View view) {
        mTvScrollTo.scrollTo(mScrollX, mScrollY);
    }

    public void scrollBy(View view) {
        mTvScrollBy.scrollBy(mScrollX, mScrollY);
    }

}
