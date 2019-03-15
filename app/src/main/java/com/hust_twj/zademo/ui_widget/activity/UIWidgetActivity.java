package com.hust_twj.zademo.ui_widget.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.ui_widget.FuParamsAdjustPanel;

/**
 * Created by hust_twj
 * on 2019/2/26
 */
public class UIWidgetActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_widget);
    }

    public void switchOrSeekBar(View view) {
        FuParamsAdjustPanel paramsAdjustPanel = new FuParamsAdjustPanel(this);
        paramsAdjustPanel.popupFromBottom();
    }

    public void goProgressBar(View view) {
        startActivity(new Intent(this, ProgressBarActivity.class));
    }

    public void goGradientRing(View view) {
        startActivity(new Intent(this, GradientRingActivity.class));

    }

}
