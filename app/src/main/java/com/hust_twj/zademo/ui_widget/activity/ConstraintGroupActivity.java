package com.hust_twj.zademo.ui_widget.activity;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    private FloatingActionButton mBtn1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_constraint_group);

        mBtnAction = findViewById(R.id.btn_action);
        mBtn1 = findViewById(R.id.btn_1);
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

        //改变一个button 属性，对另一个有影响吗？
        //报错
       /* mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBtn1.getVisibility() == View.VISIBLE) {
                    mBtn1.setVisibility(View.GONE);
                }else {
                    mBtn1.setVisibility(View.VISIBLE);
                }
            }
        });*/


    }

}
