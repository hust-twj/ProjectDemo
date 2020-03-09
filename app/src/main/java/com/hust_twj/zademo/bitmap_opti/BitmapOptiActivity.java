package com.hust_twj.zademo.bitmap_opti;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;

import com.hust_twj.zademo.R;

public class BitmapOptiActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bitmap_opti);

    }

    public void go2Original(View view){
        startActivity(new Intent(this, BitmapOriginalActivity.class));

    }

    public void go2SampleSize(View view){
        startActivity(new Intent(this, BitmapSampleSizeActivity.class));

    }

}
