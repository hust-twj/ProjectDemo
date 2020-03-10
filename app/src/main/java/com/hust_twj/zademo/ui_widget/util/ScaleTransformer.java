package com.hust_twj.zademo.ui_widget.util;

import android.os.Build;
import android.util.Log;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import org.jetbrains.annotations.NotNull;

/**
 * Description ：ViewPager 的 PageTransformer
 * Created by Wenjing.Tang on 2020/3/8.
 */
public class ScaleTransformer implements ViewPager.PageTransformer {
//    private static final float MIN_SCALE = 0.70f;
//    private static final float mMinScale = MIN_SCALE;
//    private static final float DEFAULT_CENTER = 0.5f;

//    @Override
//    public void transformPage(@NotNull View view, float position) {
//
//        int pageWidth = view.getWidth();
//        int pageHeight = view.getHeight();
//
//        view.setPivotX(pageHeight / 2.f);
//        view.setPivotY(pageWidth / 2.f);
//        if (position < -1) {
//            view.setScaleX(mMinScale);
//            view.setScaleY(mMinScale);
//            view.setPivotX(pageWidth);
//        } else if (position <= 1) {
//            if (position < 0) {
//                float scaleFactor = (1 + position) * (1 - mMinScale) + mMinScale;
//                view.setScaleX(scaleFactor);
//                view.setScaleY(scaleFactor);
//                view.setPivotX(pageWidth * (DEFAULT_CENTER + DEFAULT_CENTER * -position));
//            } else {
//                float scaleFactor = (1 - position) * (1 - mMinScale) + mMinScale;
//                view.setScaleX(scaleFactor);
//                view.setScaleY(scaleFactor);
//                view.setPivotX(pageWidth * ((1 - position) * DEFAULT_CENTER));
//            }
//        } else {
//            view.setPivotX(0f);
//            view.setScaleX(mMinScale);
//            view.setScaleY(mMinScale);
//        }
//
//        if (position < -1 || position > 1) {
//            view.setScaleX(MIN_SCALE);
//            view.setScaleY(MIN_SCALE);
//        } else if (position <= 1) { // [-1,1]
//            if (position < 0) {
//                float scaleX = 1 + 0.3f * position;
//                view.setScaleX(scaleX);
//                view.setScaleY(scaleX);
//            } else {
//                float scaleX = 1 - 0.3f * position;
//                view.setScaleX(scaleX);
//                view.setScaleY(scaleX);
//            }
//        }
//    }



    private static final float MAX_SCALE = 1f;
    private static final float MIN_SCALE = 0.9f;
    @Override
    public void transformPage(View page, float position) {
        if (position < -1) {
            position = -1;
        } else if (position > 1) {
            position = 1;
        }

        float tempScale = position < 0 ? 1 + position : 1 - position;

        float slope = (MAX_SCALE - MIN_SCALE) / 1;
        float scaleValue = MIN_SCALE + tempScale * slope;
        page.setScaleX(scaleValue);
        page.setScaleY(scaleValue);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            page.getParent().requestLayout();
        }
    }


//    private static final float MIN_SCALE = 0.8f;
//    @Override
//    public void transformPage(View page, float position) {
//        Log.e("twj124", position +"");
//        if (position < -1 || position > 1) {
//            page.setScaleX(MIN_SCALE);
//            page.setScaleY(MIN_SCALE);
//        } else if (position <= 1) { // [-1,1]
//            if (position < 0) {
//                float scaleX = 1 + 0.3f * position;
//                page.setScaleX(scaleX);
//                page.setScaleY(scaleX);
//            } else {
//                float scaleX = 1 - 0.3f * position;
//                page.setScaleX(scaleX);
//                page.setScaleY(scaleX);
//            }
//        }
//    }

}
