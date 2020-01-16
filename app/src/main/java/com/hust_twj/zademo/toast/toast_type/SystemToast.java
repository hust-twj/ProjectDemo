package com.hust_twj.zademo.toast.toast_type;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.toast.MyToast;
import com.hust_twj.zademo.toast.handlerWrapper.ToastHandlerWrapper;

import java.lang.reflect.Field;

/**
 *
 * 系统原生Toast
 * Created by Wenjing.Tang
 * on 2019/1/25
 */
public class SystemToast implements IToast, Cloneable {

    /**
     * 在{@link SystemTN#displayToast(SystemToast)}中才被初始化
     */
    private Toast mToast;
    /**
     * 优先级
     */
    private int mPriority;
    private Context mContext;
    private View mContentView;
    private int mAnimation = android.R.style.Animation_Toast;
    private int mGravity = TOAST_DEFAULT_GRAVITY;
    private int mXOffset;
    private int mYOffset;
    private @MyToast.Duration
    int mDuration = MyToast.DURATION_SHORT;
    private CharSequence mCharSequence;

    public SystemToast(@NonNull Context context) {
        mContext = context;
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (layoutInflater == null) {
            throw new RuntimeException("LayoutInflater is null!");
        }
        this.mContentView = layoutInflater.inflate(R.layout.layout_toast, null);
    }


    @Override
    public SystemToast clone() {
        SystemToast toast = null;
        try {
            toast = (SystemToast) super.clone();
            toast.mContext = this.mContext;
            toast.mContentView = this.mContentView;
            toast.mDuration = this.mDuration;
            toast.mAnimation = this.mAnimation;
            toast.mGravity = this.mGravity;
            toast.mXOffset = this.mXOffset;
            toast.mYOffset = this.mYOffset;
            toast.mPriority = this.mPriority;
            toast.mCharSequence = this.mCharSequence;
        } catch (CloneNotSupportedException mE) {
            mE.printStackTrace();
        }
        return toast;
    }

    @Override
    public void show() {
        SystemTN.instance().add(this);
    }

    @Override
    public void cancel() {
        SystemTN.instance().cancelAll();
    }

    public static void cancelAll() {
        SystemTN.instance().cancelAll();
    }

    void showInternal() {
        mToast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
        hookHandler(mToast);
        copyToToast(mToast);
        mToast.show();
    }

    void cancelInternal() {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
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
    public IToast setDuration(@MyToast.Duration int duration) {
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
     * 捕获8.0之前Toast的BadTokenException，Google在Android 8.0的代码提交中修复了这个问题
     *
     * @param toast
     */
    static void hookHandler(Toast toast) {
        if (Build.VERSION.SDK_INT >= 26) {
            return;
        }
        try {
            Field sField_TN = Toast.class.getDeclaredField("mTN");
            sField_TN.setAccessible(true);
            Field sField_TN_Handler = sField_TN.getType().getDeclaredField("mHandler");
            sField_TN_Handler.setAccessible(true);

            Object tn = sField_TN.get(toast);
            Handler preHandler = (Handler) sField_TN_Handler.get(tn);
            sField_TN_Handler.set(tn, new ToastHandlerWrapper(preHandler));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void copyToToast(Toast toast) {
        if (toast == null) {
            return;
        }
        if (mContentView != null) {
            TextView textView = (TextView) mContentView.findViewById(R.id.tv_content);
            if (textView != null) {
                textView.setText(mCharSequence);
            }
            toast.setView(mContentView);
        }
        toast.setGravity(mGravity, mXOffset, mYOffset);
        setupToastAnim(toast, mAnimation);
        if (mDuration == MyToast.DURATION_SHORT) {
            toast.setDuration(Toast.LENGTH_SHORT);
        } else if (mDuration == MyToast.DURATION_LONG) {
            toast.setDuration(Toast.LENGTH_LONG);
        }
    }

    /**
     * 设置Toast动画
     *
     * @param toast
     * @param animRes
     */
    static void setupToastAnim(Toast toast, int animRes) {
        try {
            Object mTN = getField(toast, "mTN");
            if (mTN != null) {
                Object mParams = getField(mTN, "mParams");
                if (mParams != null && mParams instanceof WindowManager.LayoutParams) {
                    WindowManager.LayoutParams params = (WindowManager.LayoutParams) mParams;
                    params.windowAnimations = animRes;
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 反射字段
     *
     * @param object    要反射的对象
     * @param fieldName 要反射的字段名称
     */
    private static Object getField(Object object, String fieldName) throws Exception {
        Field field = object.getClass().getDeclaredField(fieldName);
        if (field != null) {
            field.setAccessible(true);
            return field.get(object);
        }
        return null;
    }

}
