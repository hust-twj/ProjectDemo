package com.hust_twj.zademo.third_part.retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.LogUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description ：
 * Created by Wenjing.Tang on 2020-01-04.
 */
public class RetrofitActivity extends Activity {

    private TextView mTvResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_retrofit);

        mTvResult = findViewById(R.id.tv_result);

        request();
    }

    private void request() {
        // 在创建Retrofit实例时通过.baseUrl()设置
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .build();

        // 创建 网络请求接口 的实例
        ServiceInterface service = retrofit.create(ServiceInterface.class);

        //对 发送请求 进行封装
        Call<List<GitUsers>> call = service.getCall();

        call.enqueue(new Callback<List<GitUsers>>() {
            @Override
            public void onResponse(Call<List<GitUsers>> call, Response<List<GitUsers>> response) {
                StringBuilder stringBuilder = new StringBuilder();
                List<GitUsers> result = response.body();
                LogUtils.e("twj124", Thread.currentThread().getName() + "  " + result);
                for (int i = 0; i < result.size(); i++) {
                    stringBuilder.append(result.get(i).toString());
                }
                mTvResult.setText(stringBuilder.toString());
            }

            @Override
            public void onFailure(Call<List<GitUsers>> call, Throwable t) {
                LogUtils.e("twj124", "onFailure");
            }
        });
    }

}

