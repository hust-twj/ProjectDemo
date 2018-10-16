package com.hust_twj.zademo.toast;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static android.view.WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;

public class BooheeToast {

    private static final int LENGTH_SHORT = 0;
    private static final int LENGTH_LONG = 1;
    private static final int LENGTH_SHORT_DURATION = 2000;
    private static final int LENGTH_LONG_DURATION = 3500;
    private static AtomicInteger mAtomicInteger = new AtomicInteger(0);
    private static Handler mHandler = new Handler();
    private static BlockingQueue<BooheeToast> mQueue = new LinkedBlockingQueue();
    private final Context mContext;
    private long mDuration;
    private static final Runnable mActive = new Runnable() {
        public void run() {
            BooheeToast.activeQueue();
        }
    };
    private final Runnable mHide = new Runnable() {
        public void run() {
            BooheeToast.this.handleHide();
        }
    };
    private WindowManager.LayoutParams mParams;
    private final Runnable mShow = new Runnable() {
        public void run() {
            BooheeToast.this.handleShow();
        }
    };
    private View mView;
    private WindowManager mWM;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {
    }

    @SuppressWarnings("ResourceType")
    public BooheeToast(Context context) {
        mContext = context;
        mParams = new WindowManager.LayoutParams();
        mParams.height = -2;
        mParams.width = -2;
        mParams.format = -3;
        mParams.windowAnimations = 16973828;
        mParams.type = TYPE_SYSTEM_ALERT;
        mParams.setTitle("Toast");
        mParams.flags = 152;
        mWM = (WindowManager) context.getSystemService("window");
        mParams.packageName = context.getPackageName();
    }

    public static BooheeToast makeText(Context context, CharSequence text, int duration) {
        return new BooheeToast(context)
                .setText(text)
                .setDuration(duration)
                .setGravity(Gravity.CENTER, 0, 0);
    }

    public static BooheeToast makeText(Context context, int resId, int duration) throws
            Resources.NotFoundException {
        return makeText(context, context.getResources().getText(resId), duration);
    }

    public BooheeToast setView(View view) {
        mView = view;
        return this;
    }

    public View getView() {
        return mView;
    }

    @TargetApi(17)
    public BooheeToast setGravity(int gravity, int xOffset, int yOffset) {
        mParams.gravity = gravity;
        if ((gravity & 7) == 7) {
            mParams.horizontalWeight = 1.0f;
        }
        if ((gravity & 112) == 112) {
            mParams.verticalWeight = 1.0f;
        }
        mParams.y = yOffset;
        mParams.x = xOffset;
        return this;
    }

    public BooheeToast setDuration(int duration) {
        if (duration == LENGTH_SHORT) {
            mDuration = LENGTH_SHORT_DURATION;
        } else if (duration == LENGTH_LONG) {
            mDuration = LENGTH_LONG_DURATION;
        }
        return this;
    }

    public BooheeToast setText(int resId) {
        setText(this.mContext.getText(resId));
        return this;
    }

    @SuppressWarnings("ResourceType")
    public BooheeToast setText(CharSequence s) {
        View view = Toast.makeText(this.mContext, s, 0).getView();
        if (view != null) {
            ((TextView) view.findViewById(16908299)).setText(s);
            setView(view);
        }
        return this;
    }

    public void show() {
        mQueue.offer(this);
        if (mAtomicInteger.get() == 0) {
            mAtomicInteger.incrementAndGet();
            mHandler.post(mActive);
        }
    }

    public void cancel() {
        if (!(mAtomicInteger.get() == 0 && mQueue.isEmpty()) && equals(mQueue.peek())) {
            mHandler.removeCallbacks(mActive);
            mHandler.post(this.mHide);
            mHandler.post(mActive);
        }
    }

    private void handleShow() {
        if (mView != null) {
            if (mView.getParent() != null) {
                mWM.removeView(mView);
            }
            try{
                mParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
                mWM.addView(mView, mParams);
            }catch (WindowManager.BadTokenException e){
                e.printStackTrace();
            }
        }
    }

    private void handleHide() {
        if (mView != null) {
            if (mView.getParent() != null) {
                mWM.removeView(mView);
                mQueue.poll();
            }
            this.mView = null;
        }
    }

    private static void activeQueue() {
        BooheeToast toast = mQueue.peek();
        if (toast == null) {
            mAtomicInteger.decrementAndGet();
            return;
        }
        mHandler.post(toast.mShow);
        mHandler.postDelayed(toast.mHide, toast.mDuration);
        mHandler.postDelayed(mActive, toast.mDuration);
    }

}
