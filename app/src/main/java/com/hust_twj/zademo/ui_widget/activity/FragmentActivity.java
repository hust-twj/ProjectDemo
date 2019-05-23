package com.hust_twj.zademo.ui_widget.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hust_twj.zademo.R;

/**
 * Created by hust_twj
 * on 2019/2/26
 *
 */
public class FragmentActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragment);


        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, TestFragment.newInstance("hello world"), "f1")
                .commit();

    }

}
