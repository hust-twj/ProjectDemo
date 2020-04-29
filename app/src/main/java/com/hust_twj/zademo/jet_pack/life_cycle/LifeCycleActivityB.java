package com.hust_twj.zademo.jet_pack.life_cycle;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hust_twj.zademo.R;

/**
 * Description ：
 * Created by Wenjing.Tang on 2019-08-07.
 */
public class LifeCycleActivityB extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
    }

}
