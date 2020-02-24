package com.hust_twj.zademo.toast;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;

import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.toast.utils.ToastUtils;

public class ToastActivity extends Activity {

    private TextView mtvToast;

    private Toast sToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_toast);

        mtvToast = findViewById(R.id.tv_toast);

        mtvToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToastUtils.toast(getApplicationContext(), "显示toast");
                //BooheeToast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
                ToastUtils.toast(ToastActivity.this,"haha");
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        //子线程中有弹出过Toast，然后Toast并没有关闭，又在主线程弹出了同一个对象的toast，会造成崩溃。
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                sToast = Toast.makeText(ToastActivity.this,"子线程弹出Toast",Toast.LENGTH_SHORT);
                sToast.show();
                Looper.loop();
            }
        });
        thread.start();
    }

    /**
     * 子线程中有弹出过Toast，然后Toast并没有关闭，又在主线程弹出了同一个对象的toast，会造成崩溃。
     * 解决：不在子线程中弹出toast--子线程弹toast时，统一利用handler转移到主线程中处理
     * @param view
     */
    public void showToast(View view) {
        sToast.setText("主线程弹出Toast");
        sToast.show();
    }

}
