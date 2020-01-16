package com.hust_twj.zademo.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import androidx.annotation.IdRes;
import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by toney on 2017/5/3.
 */

public class ViewsUtil {
    private ViewsUtil() {
    }

    public static <T> T findView(View parent, @IdRes int resId) {
        return (T) parent.findViewById(resId);
    }

    /**
     * 防止重复点击
     *
     * @param target
     * @param listener
     */
    public static void preventRepeatedClicks(final View target, final View.OnClickListener listener) {
        RxView.clicks(target).throttleFirst(1, TimeUnit.SECONDS).subscribe(new Observer<Object>() {

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object object) {
                if (listener != null){
                    listener.onClick(target);
                }
            }
        });
    }

    /**
     * 判断view 是否完整显示出来
     *
     * @param view
     * @return
     */
    public static boolean hasCompleteVisible(View view) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        return view.getVisibility() == View.VISIBLE && view.getHeight() == rect.height() && rect.top > 0;
    }

    /**
     * 获取view垂直方向可见比例
     *
     * @param view view
     * @return 可见比例
     */
    public static float getViewVisibilityPercentsVertical(View view) {
        float percents = 0;
        if (view == null) {
            return percents;
        }
        int height = view.getHeight();
        if (height == 0) {
            return percents;
        }
        Rect localVisibleRect = new Rect();
        view.getLocalVisibleRect(localVisibleRect);
        if (localVisibleRect.top > 0) {
            percents = (height - localVisibleRect.top) / (float) height;
        } else if (localVisibleRect.bottom > 0 && localVisibleRect.bottom < height) {
            percents = localVisibleRect.bottom / (float) height;
        } else if (localVisibleRect.top == 0 && localVisibleRect.bottom == height) {
            percents = 1.0f;
        }
        return percents;
    }

    /**
     * 判断view 是否完整隐藏出来
     *
     * @param view
     * @return
     */
    public static boolean hasCompleteGone(View view) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        return view.getVisibility() == View.VISIBLE && 0 == rect.top;
    }


    private static int DEFAULT_PREVENT_MULTI_CLICKS_TIME = 1000;

    public static class PreventMultiClicksData {
        public int defaultPreventMultiClicksTime = DEFAULT_PREVENT_MULTI_CLICKS_TIME;
        public long lastClickItemTimeMillis = 0;

        public PreventMultiClicksData() {

        }

        public PreventMultiClicksData(int defaultPreventMultiClicksTime) {
            this.defaultPreventMultiClicksTime = defaultPreventMultiClicksTime;
        }

    }

    /**
     * 防止多个点击事件触发
     *
     * @return 是否点击有效
     */
    public static boolean isValidClick(PreventMultiClicksData multiClicksData) {
        if (multiClicksData == null) {
            return true;
        }
        long clickTime = System.currentTimeMillis();
        if (clickTime - multiClicksData.lastClickItemTimeMillis < multiClicksData.defaultPreventMultiClicksTime) {
            return false;
        } else {
            multiClicksData.lastClickItemTimeMillis = clickTime;
            return true;
        }
    }

    public static Bitmap convertViewToBitmap(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                Bitmap.Config.ARGB_8888);
        //利用bitmap生成画布
        Canvas canvas = new Canvas(bitmap);
        //把view中的内容绘制在画布上
        view.draw(canvas);
        return bitmap;
    }
}
