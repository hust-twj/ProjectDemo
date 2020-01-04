package com.hust_twj.zademo.third_part.okhttp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.LogUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OkHttp使用：同步请求
 */
public class SyncRequestActivity extends AppCompatActivity {

    private TextView mTvResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_okhttp_sync);

        mTvResult = findViewById(R.id.tv_sync_result);

        /**
         * 同步请求
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient.Builder().readTimeout(10, TimeUnit.SECONDS).build();
                Request request = new Request.Builder().url("https://www.baidu.com").get().build();
                Call call = client.newCall(request);
                try {
                    Response response = call.execute();
                    String result = response.body().string();
                    //LogUtils.e("twj124", response.body().string());
                    setText(result);

                    //mTvResult.setText("结果：" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                    //mTvResult.setText("结果： IOException");
                    setText("IOException");
                }
            }
        }).start();


    }

    private void setText(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTvResult.setText("结果：" + text);
            }
        });
    }


}
