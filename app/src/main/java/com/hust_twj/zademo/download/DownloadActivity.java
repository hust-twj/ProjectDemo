package com.hust_twj.zademo.download;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hust_twj.zademo.R;

public class DownloadActivity extends Activity implements View.OnClickListener {

    private ProgressBar mProgressBar;
    private TextView mTvLink, mTvStartDownload, mTvStopDownload;

    private static final String URL = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_down_load);

        initViews();
        initListeners();
    }

    private void initListeners() {
        mTvStartDownload.setOnClickListener(this);
        mTvStopDownload.setOnClickListener(this);
    }

    private void initViews() {
        mProgressBar = findViewById(R.id.pb);
        mTvLink = findViewById(R.id.tv_link);
        mTvStartDownload = findViewById(R.id.tv_start_download);
        mTvStopDownload = findViewById(R.id.tv_stop_download);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_start_download:
                break;
            case R.id.tv_stop_download:
                break;
            default:
                break;
        }
    }

}
