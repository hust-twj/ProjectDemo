package com.hust_twj.zademo.download;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.LogUtils;

public class DownloadActivity extends Activity implements View.OnClickListener {

    private ProgressBar mProgressBar;
    private TextView mTvLink, mTvStartDownload, mTvStopDownload;

    private static  String URL = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_down_load);

        initViews();
        initListeners();
        initData();
    }

    private void initData() {
        URL = "http://3g.163.com/links/4636";
        DownloadManager.getInstance().add(URL, new DownloadListener() {
            @Override
            public void onFinished() {
                LogUtils.e("twj125", "onFinished");
            }

            @Override
            public void onProgress(float progress) {
                LogUtils.e("twj125", "onProgress  " + progress);

            }

            @Override
            public void onPause() {
                LogUtils.e("twj125", "onPause ");

            }

            @Override
            public void onCancel() {
                LogUtils.e("twj125", "onCancel");

            }
        });
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
                DownloadManager.getInstance().download(URL);
                break;
            case R.id.tv_stop_download:
                DownloadManager.getInstance().pause(URL);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DownloadManager.getInstance().cancel(URL);
    }
}
