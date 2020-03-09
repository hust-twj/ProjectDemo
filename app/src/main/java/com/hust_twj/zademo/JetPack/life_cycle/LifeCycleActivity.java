package com.hust_twj.zademo.JetPack.life_cycle;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.hust_twj.zademo.R;

/**
 * Description ：
 * Created by Wenjing.Tang on 2019-08-07.
 */
public class LifeCycleActivity extends AppCompatActivity {

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);

        findViewById(R.id.tv_lifecycle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LifeCycleActivity.this, LifeCycleActivityB.class));
            }
        });

        getLifecycle().addObserver(new MyLifeCycleObserver());
    }

}
