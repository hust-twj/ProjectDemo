package com.hust_twj.zademo.third_part.lancet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hust_twj.zademo.R;

/**
 * Description ：
 * Created by Wenjing.Tang on 2022/1/10.
 */
public class LancetActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancet);
        Log.e("twj124", "LancetActivity onCreate called");

        findViewById(R.id.tv_lancet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("twj124", "textview click");
            }
        });
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        Log.e("twj124", "LancetActivity onStop called");
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("twj124", "LancetActivity onDestroy called");
    }
}

