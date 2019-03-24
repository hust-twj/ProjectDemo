package com.hust_twj.zademo.ui_widget.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.ui_widget.RoundLightBarView;
import com.hust_twj.zademo.ui_widget.VoiceProgressRing;


/**
 * Created by hust_twj
 * on 2019/2/26
 */
public class GradientRingActivity extends Activity implements View.OnClickListener {

    private TextView mTvAdd, mTvSub, mTvReset;
    private VoiceProgressRing mProgressRing;
    private int currentProgress = 20;

    private TextView mTvAdd2, mTvSub2, mTvReset2;
    private RoundLightBarView roundLightBarView;
    private int currentProgress2 = 20;


    private TextView mTvAdd3, mTvSub3, mTvReset3;
    private CircularProgressView mCircularProgressView;
    private int currentProgress3 = 20;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gradient_ring);

        mTvAdd = findViewById(R.id.progress_add);
        mTvSub = findViewById(R.id.progress_sub);
        mTvReset = findViewById(R.id.progress_reset);
        mProgressRing = findViewById(R.id.progress_ring);
        mProgressRing.setProgress(currentProgress);
        mTvAdd.setOnClickListener(this);
        mTvSub.setOnClickListener(this);
        mTvReset.setOnClickListener(this);

        mTvAdd2 = findViewById(R.id.progress_add2);
        mTvSub2 = findViewById(R.id.progress_sub2);
        mTvReset2 = findViewById(R.id.progress_reset2);
        mTvAdd2.setOnClickListener(this);
        mTvSub2.setOnClickListener(this);
        mTvReset2.setOnClickListener(this);
        roundLightBarView = findViewById(R.id.round_bar_view);
        roundLightBarView.setProgress(currentProgress2);

        mTvAdd3 = findViewById(R.id.progress_add3);
        mTvSub3 = findViewById(R.id.progress_sub3);
        mTvReset3 = findViewById(R.id.progress_reset3);
        mTvAdd3.setOnClickListener(this);
        mTvSub3.setOnClickListener(this);
        mTvReset3.setOnClickListener(this);
        mCircularProgressView = findViewById(R.id.circle_progress_ring);
        mCircularProgressView.setProgress(currentProgress3);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.progress_add:
                currentProgress += 5;
                if (currentProgress > 100) {
                    currentProgress = 100;
                }
                mProgressRing.setProgress(currentProgress);
                break;
            case R.id.progress_sub:
                currentProgress -= 5;
                if (currentProgress < 0) {
                    currentProgress = 0;
                }
                mProgressRing.setProgress(currentProgress);
                break;
            case R.id.progress_reset:
                currentProgress = 0;
                mProgressRing.setProgress(currentProgress);
                break;

            case R.id.progress_add2:
                currentProgress2 += 5;
                if (currentProgress2 > 100) {
                    currentProgress2 = 100;
                }
                roundLightBarView.setProgress(currentProgress2);
                break;
            case R.id.progress_sub2:
                currentProgress2 -= 5;
                if (currentProgress2 < 0) {
                    currentProgress2 = 0;
                }
                roundLightBarView.setProgress(currentProgress2);
                break;
            case R.id.progress_reset2:
                currentProgress2 = 0;
                roundLightBarView.setProgress(currentProgress2);
                break;


            case R.id.progress_add3:
                currentProgress3 += 5;
                if (currentProgress3 > 100) {
                    currentProgress3 = 100;
                }
                mCircularProgressView.setProgress(currentProgress3);
                break;
            case R.id.progress_sub3:
                currentProgress3 -= 5;
                if (currentProgress3 < 0) {
                    currentProgress3 = 0;
                }
                mCircularProgressView.setProgress(currentProgress3);
                break;
            case R.id.progress_reset3:
                currentProgress3 = 0;
                mCircularProgressView.setProgress(currentProgress3);
                break;
            default:
                break;
        }

    }
}
