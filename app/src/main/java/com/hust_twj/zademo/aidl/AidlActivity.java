package com.hust_twj.zademo.aidl;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hust_twj.zademo.R;

/**
 * Created by hust_twj
 * on 2019/3/15
 */
public class AidlActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
    }

    public void switchOrSeekBar(View view) {
    }

}
