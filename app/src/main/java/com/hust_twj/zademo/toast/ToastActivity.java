package com.hust_twj.zademo.toast;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.toast.utils.ToastUtils;

public class ToastActivity extends Activity {

    private TextView mtvToast;


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
}
