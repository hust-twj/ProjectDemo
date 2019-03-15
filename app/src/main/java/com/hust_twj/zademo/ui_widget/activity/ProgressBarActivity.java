package com.hust_twj.zademo.ui_widget.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ProgressBar;

import com.hust_twj.zademo.R;


/**
 * Created by hust_twj
 * on 2019/2/26
 *
 */
public class ProgressBarActivity extends Activity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_progress_bar);

        progressBar = findViewById(R.id.pb);
        progressBar.setProgress(50);

    }

}
