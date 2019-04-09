package com.hust_twj.zademo.ui_params;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.LogUtils;
import com.hust_twj.zademo.xfermode.ScratchCardActivity;

public class UIParamsActivity extends Activity {

    private TextView mTvUIParams;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ui_params);

        mTvUIParams = findViewById(R.id.tv_ui_params);

        mTvUIParams.post(new Runnable() {
            @Override
            public void run() {
                LogUtils.e("twj124", mTvUIParams.getLeft() + "  " + mTvUIParams.getTop() +
                        "  " + mTvUIParams.getRight() + "  " + mTvUIParams.getBottom());

                LogUtils.e("twj124", mTvUIParams.getX());
            }
        });

    }

    public void scratchCard(View view){
        startActivity(new Intent(this, ScratchCardActivity.class));

    }

}
