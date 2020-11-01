package com.hust_twj.zademo.third_part.dagger;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hust_twj.zademo.R;

import javax.inject.Inject;

/**
 * @author hust_twj
 * @date 2020/11/1
 */
public class Dagger2Activity  extends AppCompatActivity {

    @Inject
    ApiService mApiService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);

        DaggerUserComponent.create().inject(this);

        mApiService.register();
    }
}
