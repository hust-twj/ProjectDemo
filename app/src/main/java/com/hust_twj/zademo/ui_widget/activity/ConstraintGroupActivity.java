package com.hust_twj.zademo.ui_widget.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.hust_twj.zademo.R;

/**
 * Created by hust_twj
 * on 2019/2/26
 *
 */
public class ConstraintGroupActivity extends Activity {

    private FloatingActionButton mBtnAction;
    private Group mGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_constraint_group);

        mBtnAction = findViewById(R.id.btn_action);
        mGroup = findViewById(R.id.group);

        mBtnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mGroup.getVisibility() == View.VISIBLE) {
                    mGroup.setVisibility(View.GONE);
                }else {
                    mGroup.setVisibility(View.VISIBLE);
                }
            }
        });

    }

}
