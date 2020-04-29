package com.hust_twj.zademo.jet_pack.live_data;

import androidx.lifecycle.Observer;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.LogUtils;

/**
 * Description ：
 * Created by Wenjing.Tang on 2019-08-07.
 */
public class LiveDataActivity extends AppCompatActivity {

    private TextView mTvInfo;
    private UserViewModel userViewModel;
    private int age = 10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data);

        mTvInfo = findViewById(R.id.tv_info);

        userViewModel = UserViewModel.getInstance();

        userViewModel.getUser().observe(this, new Observer<UserInfo>() {
            @Override
            public void onChanged(@Nullable UserInfo userInfo) {
                if (userInfo == null) {
                    return;
                }
                Log.e("twj124", "live_data  onchange: " + userInfo.toString());
                mTvInfo.setText(userInfo.toString());
            }
        });
    }

    /**
     * 点击按钮改变信息，并同步数据
     * @param view
     */
    public void change(View view) {
        UserInfo userInfo = new UserInfo();
        userInfo.name = "zhangwuji" + age;
        age++;
        userInfo.age = age;
        userInfo.gender = age % 2;

        userViewModel.setUser(userInfo);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogUtils.e("twj124", "onConfigurationChanged");
    }

}
