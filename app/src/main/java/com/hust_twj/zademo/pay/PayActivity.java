package com.hust_twj.zademo.pay;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.share.LiveShareDialog;

/**
 * Created by Wenjing.Tang
 * on 2019/1/31
 */
public class PayActivity extends Activity {

    private TextView mTvShare;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        mTvShare = findViewById(R.id.tv_share);

        mTvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LiveShareDialog shareDialog = new LiveShareDialog(PayActivity.this);
                shareDialog.popup();

            }
        });
    }
}
