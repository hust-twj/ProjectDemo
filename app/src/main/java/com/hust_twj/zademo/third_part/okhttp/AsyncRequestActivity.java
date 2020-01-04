package com.hust_twj.zademo.third_part.okhttp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.LogUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OkHttp使用：异步请求
 */
public class AsyncRequestActivity extends AppCompatActivity {

    private TextView mTvResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_okhttp_async);

        mTvResult = findViewById(R.id.tv_async_result);

        OkHttpClient client = new OkHttpClient.Builder().readTimeout(10, TimeUnit.SECONDS).build();
        Request request = new Request.Builder().url("https://www.baidu.com").get().build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                LogUtils.e("twj124", "onFailure  线程" + Thread.currentThread().getName());
                mTvResult.setText("异步请求结果： onFailure");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                final String responseString = response.body().string();

                LogUtils.e("twj124", "onResponse 线程" + Thread.currentThread().getName());
                LogUtils.e("twj124", "onResponse " + responseString);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTvResult.setText("异步请求结果：" + responseString);
                    }
                });

            }
        });

    }

}
