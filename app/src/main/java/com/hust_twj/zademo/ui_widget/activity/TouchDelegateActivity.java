package com.hust_twj.zademo.ui_widget.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hust_twj.zademo.DemoApplication;
import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.DensityUtils;
import com.hust_twj.zademo.utils.ToastUtils;

/**
 * Description ：
 * Created by Wenjing.Tang on 2020-2-26.
 */
public class TouchDelegateActivity extends AppCompatActivity {

    public static final String TAG = "twj124";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_delegate);

        final TextView mTv = findViewById(R.id.tv_touch_delegate);
        View mParent = findViewById(R.id.layout_root);

        mParent.post(new Runnable() {
            @Override
            public void run() {
                Rect delegateArea = new Rect();
                TextView delegate = mTv;
                // Hit rectangle in parent's coordinates
                delegate.getHitRect(delegateArea);
                Log.d(TAG, "——————————原始触摸矩阵区域————————");
                Log.d(TAG, "width: " + delegateArea.width() + " | height: " + delegateArea.height());
                Log.d(TAG, "left: " + delegateArea.left + " | Top: " + delegateArea.top +
                        " | right: " + delegateArea.right + " | bottom: " + delegateArea.bottom);

                // 扩大触摸区域矩阵值
                delegateArea.left -= DensityUtils.dp2px(TouchDelegateActivity.this, 50);
                delegateArea.top -= DensityUtils.dp2px(TouchDelegateActivity.this, 50);
                delegateArea.right += DensityUtils.dp2px(TouchDelegateActivity.this, 50);
                delegateArea.bottom += DensityUtils.dp2px(TouchDelegateActivity.this, 50);

                Log.d(TAG, "——————————扩大触摸区域后  矩阵区域————————");
                Log.d(TAG, "width: " + delegateArea.width() + " | height: " + delegateArea.height());
                Log.d(TAG, "left: " + delegateArea.left + " | Top: " + delegateArea.top +
                        " | right: " + delegateArea.right + " | bottom: " + delegateArea.bottom);
                /**
                 * 构造扩大后的触摸区域对象
                 * 第一个构造参数表示  矩形面积
                 * 第二个构造参数表示 被扩大的触摸视图对象
                 * <也是本demo最核心用到的类之一>
                 */
                TouchDelegate expandedArea = new TouchDelegate(delegateArea, delegate);

                if (View.class.isInstance(delegate.getParent())) {
                    // 设置视图扩大后的触摸区域
                    ((View) delegate.getParent()).setTouchDelegate(expandedArea);
                }
            }
        });

        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "——————————点击了触摸矩阵区域————————");

            }
        });

    }


}
