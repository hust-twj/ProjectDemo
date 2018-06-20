package com.hust_twj.zademo.safety_tips;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hust_twj.zademo.R;

/**
 *
 * Created by wenjing.tang on 2018/6/15.
 */

public class SafetyDialogActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_safety_tips_dialog);
    }
}
