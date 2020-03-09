package com.hust_twj.zademo.butter_knife;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * ButterKnife使用：
 *
 * https://www.jianshu.com/p/02ca8e1ec0fc
 */
public class ButterKnifeActivity extends AppCompatActivity {

    @BindView(R.id.tv_butter_knife)
    TextView mTvButterKnife;

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_butter_knife);

        unbinder = ButterKnife.bind(this);

        mTvButterKnife.setText("haha");
    }

    @OnClick(R.id.tv_butter_knife)
    public void click(View view) {
        LogUtils.e("twj124", "点击了黄油刀");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
