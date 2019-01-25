package com.hust_twj.zademo.toast.toast_type;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.toast.MyToast;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Wenjing.Tang
 * on 2019/1/25
 */
public class CustomToast implements IToast, Cloneable {

    /**
     * 优先级
     */
    private int mPriority;
    protected Context mContext;
    private View mContentView;
    private int mAnimation = android.R.style.Animation_Toast;
    private int mGravity = TOAST_DEFAULT_GRAVITY;
    private int mXOffset;
    private int mYOffset;
    private int mWidth = WindowManager.LayoutParams.WRAP_CONTENT;
    private int mHeight = WindowManager.LayoutParams.WRAP_CONTENT;
    private @MyToast.Duration
    int mDuration = MyToast.DURATION_SHORT;
    protected boolean isShowing;
    private CharSequence mCharSequence;

    public CustomToast(@NotNull Context context) {
        mContext = context;
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (layoutInflater == null) {
            throw new RuntimeException("LayoutInflater is null!");
        }
        this.mContentView = layoutInflater.inflate(R.layout.layout_toast, null);
    }

    public WindowManager.LayoutParams getWMParams() {
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        lp.format = PixelFormat.TRANSLUCENT;
        lp.type = WindowManager.LayoutParams.TYPE_TOAST;
        lp.height = this.mHeight;
        lp.width = this.mWidth;
        lp.windowAnimations = this.mAnimation;
        lp.gravity = this.mGravity;
        lp.x = this.mXOffset;
        lp.y = this.mYOffset;
        return lp;
    }

    public WindowManager getWindwoManager() {
        if (mContext == null) {
            return null;
        }
        return (WindowManager) mContext.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
    }

    @Override
    public void show() {
        CustomTN.instance().add(this);
    }

    /**
     * 取消Toast,会清除队列中所有Toast任务
     * 因为TN中使用的是{@link this#clone()}，外部没有Toast队列中单个任务的引用，所以外部无法单独取消一个Toast任务
     */
    @Override
    public void cancel() {
        CustomTN.instance().cancelAll();
    }

    public static void cancelAll() {
        CustomTN.instance().cancelAll();
    }

    public static void cancelActivityToast(Activity mActivity) {
        CustomTN.instance().cancelActivityToast(mActivity);
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public IToast setView(View view) {
        mContentView = view;
        return this;
    }

    @Override
    public View getView() {
        return mContentView;
    }

    @Override
    public IToast setDuration(int duration) {
        mDuration = duration;
        return this;
    }

    @Override
    public int getDuration() {
        return mDuration;
    }

    @Override
    public IToast setGravity(int gravity) {
        return setGravity(gravity, 0, 0);
    }

    @Override
    public IToast setGravity(int gravity, int xOffset, int yOffset) {
        mGravity = gravity;
        mXOffset = xOffset;
        mYOffset = yOffset;
        return this;
    }

    @Override
    public int getGravity() {
        return mGravity;
    }

    public int getXOffset() {
        return mXOffset;
    }

    public int getYOffset() {
        return mYOffset;
    }

    @Override
    public IToast setAnimation(int animation) {
        mAnimation = animation;
        return this;
    }

    @Override
    public int getAnimation() {
        return mAnimation;
    }

    @Override
    public IToast setPriority(int priority) {
        mPriority = priority;
        return this;
    }

    @Override
    public int getPriority() {
        return mPriority;
    }

    @Override
    public IToast setText(CharSequence charSequence) {
        mCharSequence = charSequence;
        return this;
    }

    @Override
    public CharSequence getText() {
        return mCharSequence;
    }

    /**
     * Toast引用的contentView的可见性
     *
     * @return toast是否正在展示
     */
    public boolean isShowing() {
        return isShowing && mContentView != null && mContentView.isShown();
    }

    @Override
    public CustomToast clone() {
        CustomToast toast = null;
        try {
            toast = (CustomToast) super.clone();
            toast.mContext = this.mContext;
            toast.mContentView = this.mContentView;
            toast.mDuration = this.mDuration;
            toast.mAnimation = this.mAnimation;
            toast.mGravity = this.mGravity;
            toast.mHeight = this.mHeight;
            toast.mWidth = this.mWidth;
            toast.mXOffset = this.mXOffset;
            toast.mYOffset = this.mYOffset;
            toast.mPriority = this.mPriority;
            toast.mCharSequence = this.mCharSequence;
            if (mContentView != null) {
                TextView textView = (TextView) mContentView.findViewById(R.id.tv_content);
                if (textView != null) {
                    textView.setText(mCharSequence);
                }
            }

        } catch (CloneNotSupportedException mE) {
            mE.printStackTrace();
        }
        return toast;
    }

}
