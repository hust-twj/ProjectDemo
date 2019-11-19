package com.hust_twj.zademo.ui_widget.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.ui_widget.widget.LifecycleWindow;
import com.hust_twj.zademo.utils.LogUtils;

/**
 * Description ：
 * Created by Wenjing.Tang on 2019-11-19.
 */
public class LifecycleWindowActivity extends AppCompatActivity {

    private TextView mTv;
    private LifecycleWindow window;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle_window);

        mTv = findViewById(R.id.tv_show_dialog);

        window = new LifecycleWindow(this);


        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.popupFromBottom();
            }
        });

       getLifecycle().addObserver(window);

    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.e("twj124","onResume");
    }

}
