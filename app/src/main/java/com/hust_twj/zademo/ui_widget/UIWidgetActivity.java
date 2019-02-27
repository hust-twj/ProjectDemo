package com.hust_twj.zademo.ui_widget;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hust_twj.zademo.R;


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

}
