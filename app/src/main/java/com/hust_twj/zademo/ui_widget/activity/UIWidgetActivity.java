package com.hust_twj.zademo.ui_widget.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.ui_widget.widget.FuParamsAdjustPanel;

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

    public void goConstraintGroup(View view) {
        startActivity(new Intent(this, ConstraintGroupActivity.class));
    }

    public void goFragment(View view){
        startActivity(new Intent(this, FragmentActivity.class));
    }

    public void goListCountDown(View view) {
        startActivity(new Intent(this, ListCountDownActivity.class));
    }

    public void goArcRv(View view) {
        startActivity(new Intent(this, ArcRecycleViewActivity.class));
    }

    public void goEllipseMenu(View view) {
        startActivity(new Intent(this, EllipseMenuActivity.class));
    }

    public void goCircleMenu(View view) {
        startActivity(new Intent(this, CircleMenuActivity.class));
    }
}
